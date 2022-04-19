package com.example.simplecalculator;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Eval {
    public static String cal(String str){
        ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
        ScriptEngine scriptEngine=scriptEngineManager.getEngineByName("js");

        // 수식이 ^을 포함하는 경우 거듭제곱한 값을 수식에 삽입
        if(str.contains("^")){
            String temp=str;
            String[] arr1=temp.split("\\+|\\-|\\*|\\/|\\%");
            int powIndex=0; // 수식에서 ^의 위치
            int powResult=0; // 거듭제곱값

            for(int i=0;i< arr1.length;i++){
                int index=arr1[i].indexOf("^"); // 수식에 ^가 있는지 확인
                if(index!=-1){ // ^가 있으면 pow 함수로 거듭제곱값 구하기
                    powIndex=i;
                    String[] arr2=arr1[i].split("\\^");
                    powResult=(int)Math.pow(Integer.parseInt(arr2[0]),Integer.parseInt(arr2[1]));
                }
            }
            temp=temp.replace(arr1[powIndex],Integer.toString(powResult)); // 수식에서 (숫자^숫자)을 거듭제곱값으로 바꾸기
            str=temp;
        }
        try{
            return scriptEngine.eval(str).toString();
        }catch (Exception e){ // 수식이 잘못되면 null 반환
            e.printStackTrace();
            return null;
        }
    }
}
