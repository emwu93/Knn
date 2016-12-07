
package knn;

import java.io.*;
import java.util.*;



public class Knn {
   
    List<String> data = new ArrayList(); 
    List<String> nazwy = new ArrayList();
    Map<Integer, String> nazwy_map=new HashMap<>();
    Map<Float, String> dist_map=new HashMap<>();
    List<Float> stand_data = new ArrayList();
    List<Float> dist = new ArrayList();
    
   
    
        List<Float> stand_A = new ArrayList();
        List<Float> stand_B = new ArrayList();
        List<Float> stand_C = new ArrayList();
        List<Float> stand_D = new ArrayList();
   
    void add_data(Float a,Float b,Float c,Float d){
    
        data.add(String.valueOf(a));
        data.add(String.valueOf(b));
        data.add(String.valueOf(c));
        data.add(String.valueOf(d));
        data.add("NULL");
    
    }
    
    void load_data(String plik) throws FileNotFoundException
    {
       
       File dane = new File(plik);
       Scanner odczyt = new Scanner(dane);
       StringTokenizer token;
       
       
       while(odczyt.hasNextLine()){
           int i=0;
           token= new StringTokenizer(odczyt.nextLine(),",");
           while(token.hasMoreElements()){               
               data.add(token.nextToken());
           }
       }       
    }
    
    void distance()
    {
        int i=0; 
        int j=0;
        float distance;
        
        while(i<stand_A.size()-1){
            distance=(float)Math.sqrt(Math.pow((double)stand_A.get(stand_A.size()-1)-(double)stand_A.get(i),2)
                    +Math.pow((double)stand_B.get(stand_B.size()-1)-(double)stand_B.get(i),2)
                    +Math.pow((double)stand_C.get(stand_C.size()-1)-(double)stand_C.get(i),2)
                    +Math.pow((double)stand_D.get(stand_D.size()-1)-(double)stand_D.get(i),2));
            //dist.add(distance); 
            dist_map.put(distance,nazwy_map.get(j));
            i++; 
            j++;
        }
       //System.out.println(dist_map);
        
        
    }
   
    void standarization()
    {
        List<Float> tmp = new ArrayList();
       
        List<Float> A = new ArrayList();
        List<Float> B = new ArrayList();
        List<Float> C = new ArrayList();
        List<Float> D = new ArrayList();
        
        int i=0;
        int przebieg=0;
        int j=0;
        float minA=0.0f;
        float maxA=0.0f;        
        float minB=0.0f;
        float maxB=0.0f;
        float minC=0.0f;
        float maxC=0.0f;
        float minD=0.0f;
        float maxD=0.0f;
        
        /////Parsowanie liczb ze string'a na floata z pominieciem slow.         
        do{
            if(przebieg!=4){
                 tmp.add(Float.parseFloat(data.get(i)));
                 przebieg++;
            }
            
            else{
                nazwy_map.put(j, data.get(i));
                //nazwy.add(data.get(i));
                przebieg=0;
                j++;
            }
            i++;
         
        }while(i!=data.size());
       
        ////Wyznaczanie zbiorow danych oraz okreslanie min i max dla kazdego z nich.
       i=0;
       while(i<=(tmp.size()-4)){
           A.add(tmp.get(i));
           B.add(tmp.get(i+1));
           C.add(tmp.get(i+2));
           D.add(tmp.get(i+3));
           i+=4;
       }
        minA=Collections.min(A);
        minB=Collections.min(B);
        minC=Collections.min(C);
        minD=Collections.min(D);
        
        maxA=Collections.max(A);
        maxB=Collections.max(B);
        maxC=Collections.max(C);
        maxD=Collections.max(D);
        
        //Standaryzacja danych
        i=0;
        while(i<A.size()){
//            stand_data.add((A.get(i)-minA)/(maxA-minA));
//            stand_data.add((B.get(i)-minB)/(maxB-minB));
//            stand_data.add((C.get(i)-minC)/(maxC-minC));
//            stand_data.add((D.get(i)-minD)/(maxD-minD));
            stand_A.add((A.get(i)-minA)/(maxA-minA));
            stand_B.add((B.get(i)-minB)/(maxB-minB));
            stand_C.add((C.get(i)-minC)/(maxC-minC));
            stand_D.add((D.get(i)-minD)/(maxD-minD));
            i++;        
        }
        
        
//        System.out.println(stand_data);
//        System.out.println(minC);
//        System.out.println(maxC);
//        System.out.println(stand_data.get(2));
//        System.out.println("A= "+A+ A.size());
//        System.out.println("B= "+B);
//        System.out.println("C= "+C);
//        System.out.println("D= "+D);
    }
   
    
    void compute(int liczba){
        
       //Map<Integer, String> nearestN=new HashMap<>();
       Map<Float, String> distance1 = new HashMap<>();
       List<Float> distance=new ArrayList<>();
       float min = 0;      
       int k=liczba;
        
        
        for(int i=0;i<=k-1;i++){
           min=Collections.min(dist_map.keySet());
           distance.add(min);
           System.out.println(dist_map.get(Collections.min(dist_map.keySet())));
           dist_map.keySet().remove(min);
        }        
       
     //System.out.println(distance);
    
    }
    
    public static void main(String[] args) throws FileNotFoundException {
     
        Knn liczym=new Knn();
        
       liczym.load_data("data.txt");
       liczym.add_data(6.1f, 2.8f, 4.7f,1.2f);
       //System.out.println(liczym.data);
       liczym.standarization();
       liczym.distance();
       liczym.compute(5);
       
        
    }
    
}
