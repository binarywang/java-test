package cn.binarywang.test.jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * @author Binary Wang
 */
public class StringTest {
    @Test
    public void test() throws IOException {
        String src = "- lemon柠檬\n" + "- litchi荔枝\n" + "- litchi rind荔枝皮\n"
            + "- longan桂圆，龙眼\n" + "- longan pulp桂圆肉，龙眼肉\n" + "- loguat枇杷\n"
            + "- mandarine柑桔\n" + "- mango芒果\n" + "- mature成熟的\n"
            + "- morello黑樱桃\n" + "- muskmelon香瓜，甜瓜\n" + "- navel orange脐橙\n"
            + "- nut坚果\n" + "- nut meat坚果仁\n" + "- nut shell坚果壳\n"
            + "- oleaster沙枣\n" + "- olive橄榄\n" + "- orange柑桔\n"
            + "- orange peel柑桔皮\n" + "- papaya木瓜\n" + "- peach桃子\n"
            + "- pear梨\n" + "- perishable易腐烂的\n" + "- pineapple菠萝\n"
            + "- plum李子\n" + "- plumcot李杏\n" + "- pomegranate石榴\n"
            + "- pomelo柚子，代旦\n" + "- red bayberry杨梅\n" + "- reduced price处理价\n"
            + "- ripe成熟的\n" + "- rotten fruit烂果\n" + "- seasonable应时的\n"
            + "- seedless orange无核桔\n" + "- special-grade特级的\n"
            + "- strawberry草莓\n" + "- sultana无核小葡萄\n" + "- superfine特级的\n"
            + "- tangerine柑桔\n" + "- tart酸的\n" + "- tender嫩的\n"
            + "- tinned fruit罐头水果\n" + "- unripe未成熟的\n" + "- walnut胡桃，核桃\n"
            + "- walnut kernel核桃仁\n" + "- water chestnut荸荠\n"
            + "- watermelon西瓜\n" + "- almond杏仁\n" + "- apple苹果\n"
            + "- apple core苹果核\n" + "- apple juice苹果汁\n" + "- apple skin苹果皮\n"
            + "- apricot杏子\n" + "- apricot flesh杏肉\n" + "- apricot pit杏核\n"
            + "- areca nut槟榔子\n" + "- banana香蕉\n" + "- banana skin香蕉皮\n"
            + "- bargain price廉价\n" + "- beechnut山毛榉坚果\n"
            + "- Beijing flowering crab海棠果\n" + "- bitter苦的\n"
            + "- bitterness苦味\n" + "- bitter orange酸橙\n" + "- blackberry黑莓\n"
            + "- canned fruit罐头水果\n" + "- carambola杨桃\n" + "- cherry樱桃\n"
            + "- cherry pit樱桃核\n" + "- cherry pulp樱桃肉\n" + "- chestnut栗子\n"
            + "- Chinese chestnut板栗\n" + "- Chinese date枣\n"
            + "- Chinese gooseberry猕猴桃\n" + "- Chinese walnut山核桃\n"
            + "- coconut椰子\n" + "- coconut milk椰奶\n" + "- coconut water椰子汁\n"
            + "- cold storage冷藏\n" + "- cold store冷藏库\n" + "- crisp脆的\n"
            + "- cumquat金桔\n" + "- damson plum西洋李子\n" + "- Dangshan pear砀山梨\n"
            + "- date枣\n" + "- date pit枣核";
        Path tempFile = Files.createTempFile("", ".txt");
        //new File(Files.createTempDir(), "1.txt");
        System.err.println(tempFile);
        Files.write(tempFile, src.getBytes());
        //Files.write(src, file, Charset.defaultCharset());
        //Files.readLines(file, Charset.defaultCharset())
        List<String> lines = Lists.newArrayList();
        Files.readAllLines(tempFile).forEach(str -> {
            StringBuilder newLine = new StringBuilder();
            int firstChineseCharIndex = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) > Integer.valueOf("1000", 16)) {
                    //遇到中文字符
                    firstChineseCharIndex = i;
                    break;
                }
            }
            newLine
                .append(StringUtils.substring(str, 0, firstChineseCharIndex));
            newLine.append(" //");
            newLine.append(StringUtils.substring(str, firstChineseCharIndex));
            System.err.println(newLine);
            lines.add(newLine.toString());
        });

    }
}
