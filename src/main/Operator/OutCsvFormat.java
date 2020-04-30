package main.Operator;

import com.csvreader.*;//带入外界jar包
import main.Solution.NSSSADoubleSolutionSet;

import java.io.*;
import java.nio.charset.Charset;

public class OutCsvFormat extends Out{
    //以csv的形式格式化输出
    public static void writeCSV(NSSSADoubleSolutionSet s) {
        //csv写入方法
        // 定义一个CSV路径
        String csvFilePath = "H://StemQ.csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            String[] csvHeaders = { "编号", "最佳", "中位数","最差"};
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            SortMaoPao bubble=new SortMaoPao();
            //s=bubble.run(s);
            for (int i = 0; i < s.size(); i++) {
                double best=s.array.get(0).fitness[0];
                double a=(double)s.size();
                int b=(int)a/2;
                double middle=s.array.get(b).fitness[0];
                double worst=s.array.get(s.size()-1).fitness[0];
                String[] csvContent = { String.valueOf(i),String.valueOf(best), String.valueOf(middle),String.valueOf(worst)};
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
