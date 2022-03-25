package select.single;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SingleSelect {

    private static String questionRegex = ".*[\\(|（]\\s*([A-Z]+)\\s*[\\)|）].*";
    private static String answerRegex = "[\\(|（].*([A-Z]+).*[\\)|）]";

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = SingleSelect.class.getClassLoader();

        InputStream inputStream = null;
        try {
            inputStream = classLoader.getResourceAsStream("select.single.SingleSelect.txt");
            String data = readFromInputStream(inputStream);

            //System.out.println(data);
            List<Question> convert = convert(data);
            for (Question q : convert) {

                URL url = new URL("http://216.238.76.197:8000/api/admin/question/edit");
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setRequestProperty("Connection", "keep-alive");
                http.setRequestProperty("Pragma", "no-cache");
                http.setRequestProperty("Cache-Control", "no-cache");
                http.setRequestProperty("Accept", "application/json, text/plain, */*");
                http.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
                http.setRequestProperty("request-ajax", "true");
                http.setRequestProperty("Content-Type", "application/json");
                http.setRequestProperty("Origin", "http://216.238.76.197:8000");
                http.setRequestProperty("Referer", "http://216.238.76.197:8000/admin/index.html");
                http.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
                http.setRequestProperty("Cookie", "adminUserName=admin; Hm_lvt_cd8218cd51f800ed2b73e5751cb3f4f9=1648134945,1648216182; Hm_lpvt_cd8218cd51f800ed2b73e5751cb3f4f9=1648216182; SL_G_WPT_TO=zh; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; JSESSIONID=ryrMJUNnRo549Ttj0NL9DYDo7iDNblCflvohrt0c");
                String items = generateItems(q.getOptions());
                String payload = "{\"id\":null,\"questionType\":1,\"gradeLevel\":2,\"subjectId\":2,\"title\":\""+ q.getQuestion() +"\",\"items\":" + items + ",\"analyze\":\"<p>jiexi</p>\",\"correct\":\""+q.getAnswer()+"\",\"score\":1,\"difficult\":1}";

                byte[] out = payload.getBytes(StandardCharsets.UTF_8);

                OutputStream stream = http.getOutputStream();
                stream.write(out);

                System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
                http.disconnect();
            }

        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }


    }

    public static String generateItems(List<String> options) {
        String template = "ABCDEFGHIGKLMN";
        JSONArray arrays = new JSONArray();
        for(int i = 0; i < options.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("prefix", (char)template.getBytes()[i]);
            jsonObject.put("content", options.get(i));
            arrays.add(jsonObject);
        }
        return arrays.toJSONString();
    }

    public static List<Question> convert(String input) {
        String[] split = input.split("\n");
        Question question = null;
        List<Question> questionList = new ArrayList<>();
        for (String s : split) {
            if( isQuestionLine(s)) {
                if(Objects.nonNull(question)) {
                    questionList.add(question);
                }
                question = new Question();
                String replace = s.replaceAll(answerRegex, "( )");
                question.setQuestion(replace);
                Matcher matcher = Pattern.compile(questionRegex).matcher(s);
                if(matcher.find()) {
                    question.setAnswer(matcher.group(1));
                }
            } else {
                question.getOptions().addAll(dealOptions(s));
            }
        }
        questionList.add(question);
        System.out.println(questionList);
        return questionList;
    }

    public static List<String> dealOptions(String optionLine) {
        optionLine = optionLine.trim();
        String regex = "([A-Z][\\.|、])";
        return Arrays
                .asList(optionLine.replaceAll(regex, "\n$1").split("\n"))
                .stream()
                .filter(o -> Objects.nonNull(o) && !"".equals(o))
                .map(o -> o = o.trim())
                .collect(Collectors.toList());

    }

    public static String extractAnswer(String line) {
        Matcher matcher = Pattern.compile(questionRegex).matcher(line);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static boolean isQuestionLine(String line) {
        return line.matches(questionRegex);
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
