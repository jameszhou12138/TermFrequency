import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;
import jxl.write.WriteException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static java.util.Arrays.asList;

public class TermFrequency{
    //用于存放关键词列表。
    public static List<String>[] keyWordsList = new ArrayList[7];
    //用于存放股票代码所包含的关键词列表。
    public static HashMap<String, List<String>> stockMap = new HashMap<>();
    //用于存放输出的股票代码信息。
    public static HashMap<String, List<Integer>> stockInfo = new HashMap<>();

    //从txt中读取关键词列表
    public static void readTxt() throws IOException{
        List<String> keyWordsLists = Files.readAllLines(Paths.get("Keywords.txt"), StandardCharsets.UTF_8);
        for (int i = 1; i <= 5; i++){
            keyWordsList[i] = new ArrayList<>();
        }
        for (String keyWord: keyWordsLists){
            int wordLength = keyWord.length();
            keyWordsList[wordLength].add(keyWord);
        }
    }

    //从excel中读取股票信息
    public static void readExcel() throws Exception{
        Workbook workbook = Workbook.getWorkbook(new File("SourceText.xls"));
        Sheet sheet = workbook.getSheet(0);
        int length = sheet.getRows();
        for (int i = 1; i < length; i++){
            String stockCode = sheet.getCell(0, i).getContents();
            String stockContents = "";
            for (int j = 2; j < sheet.getColumns(); j++){
                Cell cell = sheet.getCell(j, i);
                String content = cell.getContents().trim().replace("\n", "");
                stockContents = stockContents.concat(content);
            }
            //词频统计
            getKeyWords(stockContents, stockCode);
        }
        workbook.close();
    }

    //将股票信息写入excel中
    public static void writeExcel() throws IOException, WriteException{
        WritableWorkbook workbook = Workbook.createWorkbook(new File("finalResult.xls"));
        WritableSheet sheet = workbook.createSheet("ToSheet", 0);
        String[] titles = {"股票代码", "文本长度", "关键词个数", "关键词长度", "关键词密度", "关键词列表"};
        //标题标签
        for (int i = 0; i < titles.length; i++){
            Label titleLabel = new Label(i, 0, titles[i]);
            sheet.addCell(titleLabel);
        }
        //对map的键值进行排序
        List<String> sortedStockCodeList = new ArrayList<>(stockInfo.keySet());
        sortedStockCodeList.sort(String::compareTo);
        //股票其他信息
        int length = sortedStockCodeList.size();
        for (int i = 0; i < length; i++){
            String tempCode = sortedStockCodeList.get(i);
            List<Integer> tempInfo = stockInfo.get(tempCode);
            List<String> tempKeyWords = stockMap.get(tempCode);
            //股票代码标签
            Label stockCodeLabel = new Label(0, i + 1, tempCode);
            sheet.addCell(stockCodeLabel);
            //文本长度标签
            Label txtLengthLabel = new Label(1, i + 1, String.valueOf(tempInfo.get(0)));
            sheet.addCell(txtLengthLabel);
            //关键词个数标签
            Label keyWordsNumbersLabel = new Label(2, i + 1, String.valueOf(tempInfo.get(1)));
            sheet.addCell(keyWordsNumbersLabel);
            //关键词长度标签
            Label keyWordsLengthLabel = new Label(3, i + 1, String.valueOf(tempInfo.get(2)));
            sheet.addCell(keyWordsLengthLabel);
            //密度标签
            Label densityLabel = new Label(4, i + 1, String.valueOf(1.0 * tempInfo.get(2) / tempInfo.get(0)));
            sheet.addCell(densityLabel);
            // 关键词列表标签
            String keyWords;
            if (tempKeyWords.size() != 0){
                keyWords = tempKeyWords.toString().replace("[", "").replace("]", "");
            }else{
                keyWords = "#";
            }
            Label keyWordsListLabel = new Label(5, i + 1, keyWords);
            sheet.addCell(keyWordsListLabel);
        }
        workbook.write();
        workbook.close();
    }

    //计算stockCode的stockContent中股票描述总长度、关键词数目、关键词长度、关键词密度和关键词列表
    public static void getKeyWords(String stockContent, String stockCode){
        Integer[] thisStockInfo = {0, 0, 0};
        thisStockInfo[0] = stockContent.length();
        List<Integer> stockInfoList;
        if (stockInfo.get(stockCode) != null){
            stockInfoList = stockInfo.get(stockCode);
        }else{
            stockInfoList = new ArrayList<>(asList(0, 0, 0));
        }
        List<String> stockKeyWordsList;
        if (stockMap.get(stockCode) != null){
            stockKeyWordsList = stockMap.get(stockCode);
        }else{
            stockKeyWordsList = new ArrayList<>();
        }
        //BF算法获取关键词列表
        int length = stockContent.length();
        for (int i = 0; i < length; i++){
            int l = 1, r = 5;
            if (r > length - i){
                r = length - i;
            }
            for (int len = l; len <= r; len++){
                if (i + len > length){
                    break;
                }
                String tempWord = stockContent.substring(i, i + len);
                boolean checked = false;
                for (String keyWord: keyWordsList[len]){
                    if (keyWord.equals(tempWord)){
                        stockKeyWordsList.add(keyWord);
                        thisStockInfo[1]++;
                        thisStockInfo[2] += keyWord.length();
                        checked = true;
                        break;
                    }
                }
                if (checked){
                    i += tempWord.length() - 1;
                    break;
                }
            }
        }
        for (int i = 0; i < thisStockInfo.length; i++){
            int tempKey = stockInfoList.get(i);
            stockInfoList.set(i, tempKey + thisStockInfo[i]);
        }
        stockMap.put(stockCode, stockKeyWordsList);
        stockInfo.put(stockCode, stockInfoList);
    }

    public static void main(String[] args) throws Exception{
        readTxt();
        readExcel();
        writeExcel();
        System.out.println("词频统计结束");
    }
}