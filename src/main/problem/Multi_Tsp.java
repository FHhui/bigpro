package main.problem;

import main.Solution.SSAMultiTspSolution;
import main.Solution.SSATspSolution;

import java.io.*;

public class Multi_Tsp extends Multiproblem{
    //多目标tsp问题
    public int cityNum;//城市数目
    public static final double NON_CONNECTED = Double.POSITIVE_INFINITY ;
    //public int scale;//规模
    public double [][] costMatrix;//花费矩阵
    public double[][] distance;//距离矩阵
    public Multi_Tsp(int cityNum,String filename1,String filename2) {
        this.cityNum = cityNum;
        try {
            distance=init_matrix(filename1);
            //初始化距离矩阵
            costMatrix=init_matrix(filename2);
            //初始化消费矩阵
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Multi_Tsp(int cityNum,String filename1) {
        try {
            this.cityNum=cityNum;
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(filename1));
            this.cityNum = Integer.parseInt(br.readLine());

            this.distance = new double [cityNum][cityNum];
            this.costMatrix = new double [cityNum][cityNum];

            for (int i = 0; i < this.cityNum; i++) {
                for (int j = 0; j < this.cityNum; j++) {
                    this.distance[i][j] = Double.POSITIVE_INFINITY;
                    this.costMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }

            for(String line; (line = br.readLine()) != null; ) {
                System.out.println("111111111111111111111111");
                String[] tokens = line.split(" ");
                int origin = Integer.parseInt(tokens[0]);
                int destin = Integer.parseInt(tokens[1]);
                int distan = Integer.parseInt(tokens[2]);
                double time = Double.parseDouble(tokens[3]);
                int nodeid = Integer.parseInt(tokens[4]);
                this.distance[origin][destin] = distan;
                this.costMatrix[origin][destin] = time;
            }
        } catch (Exception e) {
            new Exception("MultiobjectiveTSPInitializer.readProblem(): error when reading data file " + e);
        }
    }

    private double [][] init_matrix(String file) throws IOException {
        double [][] matrix = null;

        InputStream in = getClass().getResourceAsStream(file);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        StreamTokenizer token = new StreamTokenizer(br);
        try {
            boolean found ;
            found = false ;

            token.nextToken();
            while(!found) {
                if ((token.sval != null) && ((token.sval.compareTo("DIMENSION") == 0)))
                    found = true ;
                else
                    token.nextToken() ;
            }

            token.nextToken() ;
            token.nextToken() ;

            cityNum =  (int)token.nval ;

            matrix = new double[ cityNum][ cityNum] ;

            // Find the string SECTION
            found = false ;
            token.nextToken();
            while(!found) {
                if ((token.sval != null) &&
                        ((token.sval.compareTo("SECTION") == 0)))
                    found = true ;
                else
                    token.nextToken() ;
            }

            double [] c = new double[2* cityNum] ;

            for (int i = 0; i < cityNum; i++) {
                token.nextToken() ;
                int j = (int)token.nval ;

                token.nextToken() ;
                c[2*(j-1)] = token.nval ;
                token.nextToken() ;
                c[2*(j-1)+1] = token.nval ;
            } // for

            double dist ;
            for (int k = 0; k < cityNum; k++) {
                matrix[k][k] = 0;
                for (int j = k + 1; j < cityNum; j++) {
                    dist = Math.sqrt(Math.pow((c[k*2]-c[j*2]),2.0) +
                            Math.pow((c[k*2+1]-c[j*2+1]), 2));
                    dist = (int)(dist + .5);
                    matrix[k][j] = dist;
                    matrix[j][k] = dist;
                    //System.out.println(dist);
                }
            }
        } catch (Exception e) {
            new IOException();
        }
        return matrix;
    }
    public SSAMultiTspSolution evalute(SSAMultiTspSolution s){
        double fitness1   ;
        double fitness2   ;

        fitness1 = 0.0 ;
        fitness2 = 0.0 ;

        for (int i = 0; i < (cityNum - 1); i++) {
            int x ;
            int y ;

            x = s.city_cycle.get(i) ;
            y = s.city_cycle.get(i+1) ;

            if (distance[x][y] != NON_CONNECTED) {
                fitness1 += distance[x][y];
                fitness2 += costMatrix[x][y];
            }
        }
        int firstCity ;
        int lastCity  ;

        firstCity = s.city_cycle.get(0) ;
        lastCity = s.city_cycle.get(cityNum - 1) ;

        if (distance[firstCity][lastCity] != NON_CONNECTED) {
            fitness1 += distance[firstCity][lastCity];
            fitness2 += costMatrix[firstCity][lastCity];
        }

        s.fitness[0]= fitness1;
        s.fitness[1]= fitness2;
//        double len=0;
//        for (int i=1;i<cityNum;i++){
//            len+=distance[s.city_cycle.get(i-1)][s.city_cycle.get(i)];
//        }
//        len+=distance[s.city_cycle.get(cityNum-1)][s.city_cycle.get(0)];
//        s.fitness[0]=len;
//
//        double len2=0;
//        for (int i=1;i<cityNum;i++){
//            len2+=costMatrix[s.city_cycle.get(i-1)][s.city_cycle.get(i)];
//        }
//        len2+=costMatrix[s.city_cycle.get(cityNum-1)][s.city_cycle.get(0)];
//        s.fitness[1]=len2;
//        return s;
        return s;
    }


}
