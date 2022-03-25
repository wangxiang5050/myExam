import org.junit.Assert;
import org.junit.Test;
import select.multiple.MultipleSelect;

import java.util.List;

public class MultipleSelectTest {

    @Test
    public void generateItemsTest() {
        List<String> strings = MultipleSelect.dealOptions("A.公安部门 \n" +
                "B.人民政府监察部门                                                                                                      \n" +
                "C.企业管理部门 \n");
        System.out.println(MultipleSelect.generateItems(strings));
    }

    @Test
    public void convertTest() {
        MultipleSelect.convert("1. 党的十九届六中全会总结出，建党百年宝贵历史经验总结，“十个坚持”指的是：（ABCDE）\n" +
                "A.坚持党的领导，坚持人民至上；\n" +
                "B.坚持理论创新，坚持独立自主；\n" +
                "C.坚持中国道路，坚持胸怀天下；\n" +
                "D.坚持开拓创新，坚持敢于斗争；\n" +
                "E.坚持统一战线，坚持自我革命。\n" +
                "2.党的十九届六中全会总结出，建党百年四个“伟大成就”，指的是：（ABCD）\n" +
                "A.党领导人民浴血奋战、百折不挠，创造了新民主主义革命的而伟大成就；\n" +
                "B.党领导人民自力更生、发愤图强，创造了社会主义革命和建设的伟大成就；\n" +
                "C.党领导人民解放思想、锐意进取，创造了改革开放和社会主义现代化建设的伟大成就；\n" +
                "D.党领导人民自信自强、守正创新，创造了新时代中国特色社会主义的伟大成就。\n" +
                "3.党支部肩负哪些职责？（ABCDEFG）\n" +
                "A.教育党员\n" +
                "B.管理党员\n" +
                "C.监督党员\n" +
                "D.组织群众\n" +
                "E.宣传群众\n" +
                "F.凝聚群众\n" +
                "G.服务群众。 \n" +
                "4.党的民主集中制的基本原则是：（ABCD）\n" +
                "A.党员个人服从党的组织、\n" +
                "B.少数服从多数、\n" +
                "C.下级组织服从上级组织、\n" +
                "D.全党各个组织服从党的全国代表大会和中央委员会。\n" +
                "5.党面临的四大考验是:（ABCD）\n" +
                "A.执政考验\n" +
                "B.改革开放考验\n" +
                "C.市场经济考验\n" +
                "D.外部环境考验\n" +
                "6.党面临的四大危险是:（ABCD）\n" +
                "A.精神懈怠危险\n" +
                "B.能力不足危险、\n" +
                "C.脱离群众危险、\n" +
                "D.消极腐败危险。\n" +
                "7.国有企业领导人员必须做到: （ABCDE）\n" +
                "A.对党忠诚\n" +
                "B.勇于创新\n" +
                "C.治企有方\n" +
                "D.兴企有为\n" +
                "E.清正廉洁。\n" +
                "8.出台中央八项规定，严厉整治    、    、   和   ，坚决反对特权。(ABCE)\n" +
                "A.形式主义  \n" +
                "B.官僚主义  \n" +
                "C.享乐主义  \n" +
                "D.个人主义\n" +
                "E.奢靡之风\n" +
                "9.加强各级领导班子建设，    党和人民需要的好干部，培养和造就千百万社会主义事业接班人，从组织上保证党的基本      理论、基本路线、     的贯彻落实。(BC)\n" +
                "A.选拔使用\n" +
                "B.培养选拔\n" +
                "C.基本方略     \n" +
                "D.基本纲领\n" +
                "10.          和      是中国共产党人的精神支柱和政治灵魂，也是保持党的团结统一的思想基础。(AB)\n" +
                "A.共产主义远大理想 \n" +
                "B.中国特色社会主义共同理想\n" +
                "C.共产主义崇高理想 \n" +
                "D.新时代中国特色社会主义共同理想");
    }

    @Test
    public void dealOptionsTest() {
        List<String> strings = MultipleSelect.dealOptions("A.形式主义  \n" +
                "B.官僚主义  \n" +
                "C.享乐主义  \n" +
                "D.个人主义\n" +
                "E.奢靡之风");
        System.out.println(strings);
        List<String> strings1 = MultipleSelect.dealOptions("A、党的基本路线 \n" +
                "B、党的路线方针政策\n" +
                "C、民主集中制  \n" +
                "D、重大问题请示报告制度");
        System.out.println(strings1);
    }

    @Test
    public void isQuestionLineTest() {
        String question1 = "1.根据《安全生产法》的规定，生产经营单位与从业人员订立的劳动合同应当载明的事项有( BCE )。";
        Assert.assertTrue(MultipleSelect.isQuestionLine(question1));
        String question2 = "3.职工有下列( ACD )情形的，视同工伤。";
        Assert.assertTrue(MultipleSelect.isQuestionLine(question2));
        String question3 = "1.《武器装备质量管理条例》是为了加强对武器装备质量的监督管理，提高武器装备质量水平，根据(   )和（ ）而制定得。（ AB ）";
        Assert.assertTrue(MultipleSelect.isQuestionLine(question3));
        String question4 = "19.依据《消防法》的规定，火灾扑灭后，为隐瞒、掩饰起火原因，推卸责任，故意破坏现场或者伪造现场，尚不构成犯罪的，可以给予的行政处罚包括(　ACE　)。\n";
        Assert.assertTrue(MultipleSelect.isQuestionLine(question4));
    }

    @Test
    public void extractAnswerTest() {
        String question1 = "1.《武器装备质量管理条例》是为了加强对武器装备质量的监督管理，提高武器装备质量水平，根据(   )和（ ）而制定得。（ AB ）";
        Assert.assertEquals(MultipleSelect.extractAnswer(question1), "AB");
        String question2 = "4.GJB9001C标准规定：质量目标应（ABCDE）。   \n";
        Assert.assertEquals(MultipleSelect.extractAnswer(question2), "ABCDE");
        String question3 = "8.出台中央八项规定，严厉整治    、    、   和   ，坚决反对特权。(ABCE)";
        Assert.assertEquals(MultipleSelect.extractAnswer(question3), "ABCE");
    }

}
