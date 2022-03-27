import blank.Blank;
import org.junit.Test;

public class BlankTest {

    @Test
    public void extractAnswerTest() {
        System.out.println(Blank.extractAnswer("1.《安全生产法》规定，生产经营单位采用新<span>工艺</span>、新<span>技术</span>、新<span>材料</span>或者使用新设备，必须了解、掌握其安全技术特征，采取有效的安全防护措施等。\n"));
    }

    @Test
    public void convertTest() {
        Blank.convert("1.《安全生产法》规定，生产经营单位采用新<span>工艺</span>、新<span>技术</span>、新<span>材料</span>或者使用新设备，必须了解、掌握其安全技术特征，采取有效的安全防护措施等。\n" +
                "2.《中华人民共和国环境保护法》规定，每年<span>6</span>月<span>5</span>日为环境日。\n" +
                "3. 安全生产许可证的有效期为<span>3</span>年。\n" +
                "4.《安全生产法》规定，生产经营项目有多个承包单位、承租单位的，生产经营单位对承包单位、承租单位的安全生产工作统一<span>协调、管理</span>。\n" +
                "5.《劳动法》规定，劳动者对用人单位管理人员违章指挥、强令冒险作业，有权<span>拒绝执行</span>。\n" +
                "6. 一般事故上报至<span>市</span>级人民政府安全生产监督管理部门和负有安全生产监督管理职责的有关部门。\n" +
                "7. 生产经营单位主要负责人和安全生产管理人员初次安全培训时间不得少于<span>32</span>学时。每年再培训时间不得少于<span>12</span>学时。\n" +
                "8. 在生产经营单位的安全生产工作中，最基本的安全管理制度是<span>安全生产责任制</span>。\n" +
                "9.《安全生产法》规定，生产经营单位必须对安全设备进行<span>经常性</span>维护、保养。\n" +
                "10.《安全生产法》规定的安全生产违法行为的法律责任形式，包括<span>行政</span>责任、<span>民事</span>责任和<span>刑事</span>责任。\n" +
                "11.依据《安全生产许可证条例》的规定，从事矿山、建筑施工和<span>危险化学品</span>、烟花爆竹、民用爆破器材生产的企业，应当依法取得安全生产许可证。\n" +
                "12.据《生产安全事故报告和调查处理条例》的规定，自事故发生之日起<span>30</span>日内，事故造成的伤亡人数发生变化的，事故发生单位应当及时补报。\n" +
                "13.根据《突发环境事件应急预案管理办法》的规定，编制应急预案就当在开展<span>环境风险</span>评估和环境应急资源调查的基础上进行。\n" +
                "14.2021年世界环境日中国主题是<span>人与自然和谐共生</span>。\n" +
                "15.《消防法》规定，消防工作贯彻<span>预防为主、防消结合</span>的方针。\n" +
                "16.同一建筑物由两个以上单位管理或者使用的，应当明确<span>各方的</span>消防安全责任，并确定责任人对共用的疏散通道、安全出口、建筑消防设施和消防车通道进行统一管理。\n" +
                "17.生产、储存、运输、销售、使用、销毁<span>易燃易爆物品</span>，必须执行消防技术标准和管理规定。\n" +
                "18.根据《工伤保险条例》的规定，一次性工亡补助金标准为上一年度全国城镇居民人均可支配收入的<span>20</span>倍。\n" +
                "19.依据《工伤保险条例》的规定，工伤保险补偿实行<span>无过错</span>补偿的原则。\n" +
                "20.可以预警的自然灾害、事故灾难和公共卫生事件的预警级别，按照突发事件发生的紧急程度、发展势态和可能造成的危害程度分为一级、二级、三级和四级，<span>一级</span>为最高级别，用<span>红</span>色标示。");

    }

}
