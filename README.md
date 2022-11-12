# TermFrequency
## 实验题目

词频统计          

## 实验环境

Intellij IDEA         

## 实验要求

1. 掌握String类及其常用成员方法，掌握HashSet类、ArrayList类。

2. 掌握文件的读写方法。

3. 掌握使用Java的API（jxl.jar）访问Excel的方法。

4. 实验实现目标：对Excel文件中的文本字段，找出其中所有的关键词，统计其数量和在文本中的密度。

## 实验内容

### 1．实验背景介绍

（1）股票交易所每年都会聘请审计机构对各支股票进行审计，生成简单的审计报告。我们需要从审计报告文本中通过关键字匹配得到各支股票的风险值。

（2）文件“SourceText.xls”中包含所有的审计信息。第1列是股票代号，同一个股票代号会出现在多行中，其实一支股票可以出现在多年的审计结果中，每一年中会有多个审计记录，保存在多个行中。因此，总体上，一支股票会出现在若干行中。第2列是会计年，也就是审计了哪一年的股票情况。第3列、第4列、第5列是审计生成的报告结果。

（3）文件“Keywords.txt”中包含了与风险相关的一百多个关键词，凡是出现了这其中的关键词，就认为股票有风险。审计文本中出现的关键词的次数越多，被认为股票的风险越高。

（4）本实验要对每一支股票的所有审计文本中的所有关键词按照顺序列出来（有可能重复）。例如，某支股票的文本中有可能出现“严重”、“困难”、“产能过剩”、“困难”等4个关键词，那么出现的关键词列表就是“严重,困难,产能过剩,困难”

（5）对每一支股票，需要统计3个量：第一个量是文本的总长度（也就是总字数），但不包括回车、换行、文本其实和末尾的空格等4种符号；第二个量就是出现的关键词的数量，例如对上述例子，关键词的数量是4；第三个量是关键词的总长度，也就是所有出现的关键词的长度之和，对于上述的例子，这个值是2+2+4+2=10。

（6）最后，我们要计算关键词的密度，也就是说关键词的总长度在文本总长度中所占的比重。对于上述例子，如果文本的总长度是100，那么关键词的密度就是10/100=0.1，认为该支股票的风险是0.1。

（7）依据文件“Keywords.txt”中的信息（输入），对文件“SourceText.xls”（输入）中的信息进行处理统计，获得一个结果文件“Result.xls”。这个文件中有6列，第1列是股票代号，要按照从小到大的顺序排列；第2列是该股票审计描述文本的总长度；第3列是审计文本中出现的关键词总数；第4列是出现的关键词总长度；第5列是关键词的密度；第6列是文本中按照顺序出现的所有关键词，中间用英文的逗号隔开。

 

### 2．用记事本书写一个Java程序

（1）建立个人子目录

步骤1：建立个人子目录

第一次上机时先在D盘上建立一个以自己学号+姓名为目录名的子目录，如学号为210824109的张三同学，就用“210824109张三”为子目录名。实验完成的源代码、Java字节码和实验报告三个文件都要放在这个文件夹下（称为上交文件夹）。

步骤2：建立Java源代码文件

在所建立的文件夹下建立一个记事本文件TermFrequency.txt，并把它重命名为TermFrequency.java

（2）编写源代码

步骤1：创建一个公共类TermFrequency

要创建的公共类在默认包中，可引入其它的包。所创建的公共类在文件中的一行如下：

public class TermFrequency{… …}

步骤2：建立主方法main( )

在类TermFrequency的类体中编写主方法：

public static void main(String[] args){… …}

步骤3：编写方法和Fraction的主方法main( )

主方法用于测试。辅助方法的方法头请参见附件

步骤4：注意引入“jxl.jar”包，用该API可以访问Excel文件，当然你可以引入另外的访问Excel文件的jar包。

 

3．调试和运行

（1）调试

步骤1：使用命令行工具，先进入用所建的目录下。

步骤2：用javac TermFrequency.java编译并调试源代码文件。

（2）运行

使用java TermFrequency D:/SourceText.xls Sheet1 Result.xls finalResult运行程序。其中第一个参数“D:/SourceText.xls”是输入数据所在的Excel文件，“Sheet1”是原始数据文件中存放数据的表格的名称；“Result.xls”是运算结果存放的文件，“finalResult”是要存放结果的表格的名称。

 

## 实验方法

根据实验内容，将该实验分为三个步骤：（1）定义所需的变量类型和名称；（2）编写读取写入文件方法；（3）编写股票词频统计方法。

 

## 实验结果

（1）将Keywords.txt、SourceText.xls放入同一目录下，在Intellij IDEA中运行程序，开始进行词频统计。程序运行结束时，输出“词频统计结束”的提示。（如图1所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml9832\wps1.jpg) 

图1 程序运行

 

（2）程序运行结束后，可以发现在该目录下生成了一个名为finalResult.xls文件。（如图2所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml9832\wps2.jpg) ![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml9832\wps3.jpg)

图2 生成文件

 

（2）打开finalResult.xls文件，与Result.xls比较，两者相同。（如图2所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml9832\wps4.jpg) ![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml9832\wps5.jpg)

图3 finalResult.xls（左）和Result.xls（右）

 

 

## 结论分析

（1）读取txt文本文件：

导入java.nio.file.Paths包，获取Keywords.txt文件的文件位置。之后，导入java.nio.file.Files包，使用该包下的readAllLines方法读取txt文本文件。再根据关键字的长度进行分组。KeyWordList[i]表示长度为i的关键词列表。

```java
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
```

 

（2）读取excel表格文件：

导入jxl.Workbook包，使用该包下的getWorkbook方法获取SourceText.xls表格文件；导入jxl.Sheet包，使用该包下的getSheet方法获取excel表格的0号工作表；导入jxl.Cell包，使用该包下的getCell方法获取工作表的每一个单元格。对于每一行，第一个为股票代码，剩下的全为股票信息。统计该股票的词频。最后使用jxl.Workbook包下的close方法关闭文件。

```java
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
    getKeyWords(stockContents, stockCode);
  }
  workbook.close();
}
```

 

（3）写入excel表格文件：

导入jxl.Workbook包，使用该包下的createWorkbook方法表格文件；导入jxl.write.WritableWorkbook包，使用该包下的createSheet方法在”inalResult.xls表格文件创建名为”ToSheet”的工作表；导入jxl.write.WritableSheet包，使用该包下的addCell方法在工作表中加入标题行；导入jxl.write.Label包，对于每一行，用该包下的构造函数写入股票代码、文本长度、关键词个数、关键词长度、关键词密度和关键词列表信息，再使用jxl.write.Label包下的addCell方法将这些信息放入工作表中。然后使用jxl.Workbook包下的write方法写入文件，最后使用jxl.Workbook包下的close方法关闭文件。

```java
//将股票信息写入excel中
public static void writeExcel() throws IOException, WriteException{
  WritableWorkbook workbook = Workbook.createWorkbook(new File("finalResult.xls"));
  WritableSheet sheet = workbook.createSheet("ToSheet", 0);
  String[] titles = {"股票代码", "文本长度", "关键词个数", "关键词长度", "关键词密度", "关键词列表"};
  for (int i = 0; i < titles.length; i++){
    Label titleLabel = new Label(i, 0, titles[i]);
    sheet.addCell(titleLabel);
  }   List<String> sortedStockCodeList = new ArrayList<>(stockInfo.keySet());
  sortedStockCodeList.sort(String::compareTo);
  int length = sortedStockCodeList.size();
  for (int i = 0; i < length; i++){
    String tempCode = sortedStockCodeList.get(i);
    List<Integer> tempInfo = stockInfo.get(tempCode);
    List<String> tempKeyWords = stockMap.get(tempCode);
    Label stockCodeLabel = new Label(0, i + 1, tempCode);
    sheet.addCell(stockCodeLabel);
    Label txtLengthLabel = new Label(1, i + 1, String.valueOf(tempInfo.get(0)));
    sheet.addCell(txtLengthLabel);
    Label keyWordsNumbersLabel = new Label(2, i + 1, String.valueOf(tempInfo.get(1)));
    sheet.addCell(keyWordsNumbersLabel);
    Label keyWordsLengthLabel = new Label(3, i + 1, String.valueOf(tempInfo.get(2)));
    sheet.addCell(keyWordsLengthLabel);
    Label densityLabel = new Label(4, i + 1, String.valueOf(1.0 * tempInfo.get(2) / tempInfo.get(0)));
    sheet.addCell(densityLabel);
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
```

 

（4）词频统计：

对于每支股票，得到其所有的审计报告文本。BF算法获取股票总长度、关键词数目、关键词长度、关键词密度和关键词列表——对于文本的每一小段文字（长度为1~5），判断是否在其对应长度的关键词列表中出现，若出现，则将其放入关键词列表中，关键字数量加1，关键词长度加该段文字的长度。最终将其放入HashMap中。

```java
//计算stockCode的stockContent中股票总长度、关键词数目、关键词长度、关键词密度和关键词列表
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
```

 

 
