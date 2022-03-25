import org.junit.Assert;
import org.junit.Test;
import select.single.SingleSelect;

import java.util.List;

public class UtilTest {

    @Test
    public void generateItemsTest() {
        List<String> strings = SingleSelect.dealOptions("A.公安部门 \n" +
                "B.人民政府监察部门                                                                                                      \n" +
                "C.企业管理部门 \n");
        System.out.println(SingleSelect.generateItems(strings));
    }

    @Test
    public void convertTest() {
        SingleSelect.convert("9. 根据《中国共产党纪律处分条例》规定，党的各级代表大会的代表受到(D)处分的，党组织应当终止其代表资格。\n" +
                "A.警告(含)以上          B.严重警告(含)以上\n" +
                "C.撤销党内职务(含)以上  D.留党察看(含)以上");
    }

    @Test
    public void dealOptionsTest() {
        List<String> strings = SingleSelect.dealOptions("A.周末工作     B.加班     C.白班劳动     D.夜班劳动");
        System.out.println(strings);
        List<String> strings1 = SingleSelect.dealOptions("A、党的基本路线 \n" +
                "B、党的路线方针政策\n" +
                "C、民主集中制  \n" +
                "D、重大问题请示报告制度");
        System.out.println(strings1);
    }

    @Test
    public void isQuestionLineTest() {
        String question1 = "1.依照《安全生产法》的规定，生产经营单位发生事故后，必须立即如实报告当地（ D ）。";
        Assert.assertTrue(SingleSelect.isQuestionLine(question1));
        String question2 = "1.依照《安全生产法》的规定，生产经营单位发生事故后，必须立即如实报告当地( D)。";
        Assert.assertTrue(SingleSelect.isQuestionLine(question2));
        String question3 = "A.警告(含)以上          B.严重警告(含)以上";
        Assert.assertFalse(SingleSelect.isQuestionLine(question3));
    }

    @Test
    public void extractAnswerTest() {
        String question1 = "1.依照《安全生产法》的规定，生产经营单位发生事故后，必须立即如实报告当地（ D ）。";
        Assert.assertEquals(SingleSelect.extractAnswer(question1), "D");
        String question2 = "1.依照《安全生产法》的规定，生产经营单位发生事故后，必须立即如实报告当地( D)。";
        Assert.assertEquals(SingleSelect.extractAnswer(question2), "D");
    }

}
