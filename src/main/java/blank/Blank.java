package blank;

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

public class Blank {

    private static String questionRegex = ".*";
    private static String answerRegex = "(<span[^>]*>)([^>]*)(<\\/span>)";

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Blank.class.getClassLoader();

        InputStream inputStream = null;
        try {
            inputStream = classLoader.getResourceAsStream("Blank.txt");
            String data = readFromInputStream(inputStream);

            //System.out.println(data);
            List<BlankQuestion> convert = convert(data);
            for (BlankQuestion q : convert) {

                URL url = new URL("http://216.238.76.197:8000/api/admin/question/edit");
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setRequestProperty("Connection", "keep-alive");
                http.setRequestProperty("Pragma", "no-cache");
                http.setRequestProperty("Cache-Control", "no-cache");
                http.setRequestProperty("Accept", "application/json, text/plain, */*");
                http.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.83 Safari/537.36");
                http.setRequestProperty("request-ajax", "true");
                http.setRequestProperty("Content-Type", "application/json");
                http.setRequestProperty("Origin", "http://216.238.76.197:8000");
                http.setRequestProperty("Referer", "http://216.238.76.197:8000/admin/index.html");
                http.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
                http.setRequestProperty("Cookie", "adminUserName=admin; studentUserName=pingping; Hm_lvt_cd8218cd51f800ed2b73e5751cb3f4f9=1648134945,1648216182,1648272515,1648343141; Hm_lpvt_cd8218cd51f800ed2b73e5751cb3f4f9=1648343141; SL_G_WPT_TO=zh; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; JSESSIONID=vFLqOVW-pAJz3S6gqSG7rlcA3XInYonGDnEWAoMd");

                String payload = "{\"id\":null,\"questionType\":4,\"gradeLevel\":1,\"subjectId\":1,\"title\":\""+q.getQuestion()+"\",\"items\":"+q.getAnswer()+",\"analyze\":\"a\",\"correct\":\"\",\"score\":0,\"difficult\":1}";
                System.out.println(payload);
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


    public static List<BlankQuestion> convert(String input) {
        String[] split = input.split("\n");
        List<BlankQuestion> questionList = new ArrayList<>();
        for (String s : split) {
            if( isQuestionLine(s)) {
                questionList.add(extractAnswer(s));
            }
        }

        System.out.println(questionList);
        return questionList;
    }

    public static List<String> dealOptions(String optionLine) {
        return null;
    }

    public static BlankQuestion extractAnswer(String line) {
        Matcher matcher = Pattern.compile(answerRegex).matcher(line);
        BlankQuestion blankQuestion = new BlankQuestion();
        blankQuestion.setQuestion(line);
        JSONArray answer = new JSONArray();
        int i = 1;
        while(matcher.find()) {
            String uuid = UUID.randomUUID().toString();
            blankQuestion.setQuestion(blankQuestion.getQuestion().replaceAll("(<span[^>]*>)" + matcher.group(2) + "(<\\/span>)", "<span class="+"\\\\"+"\"gapfilling-span " + uuid + ""+"\\\\"+"\">" + i + "$2"));
            JSONObject item = new JSONObject();
            item.put("itemUuid", uuid);
            item.put("prefix", Integer.toString(i));
            item.put("content", matcher.group(2));
            item.put("score", 0);
            answer.add(item);
            i++;
        }
        blankQuestion.setAnswer(JSONArray.toJSONString(answer));
        return blankQuestion;
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
