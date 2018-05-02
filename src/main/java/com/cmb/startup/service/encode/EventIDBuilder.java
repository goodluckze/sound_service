package com.cmb.startup.service.encode;

public class EventIDBuilder {



//    private static Character replaceChar = 'a';
    private static Character replaceChar = '1';

    private static final int EVENTID_COUNT = 5;
    public static String getEventId(Long id){


        char[] idCharArray = id.toString().toCharArray();

        // 遍历数据将连续重复的数字用1-5替换
        replaceChar = '1'-1;
        for(int i=0; i<idCharArray.length; i++){
            if( i > 0 ){
                idCharArray[i] = compare(idCharArray[i],idCharArray[i-1]);
            }
        }
        /*
        // 遍历数据将连续重复的数字用a-z替换
        replaceChar = 'a'-1;
        for(int i=0; i<idCharArray.length; i++){
            if( i > 0 && idCharArray[i] == idCharArray[i-1]){
                idCharArray[i] = getReplaceChar();
            }
        }
        */
        // 不足5位用随机数补足
        String ramdomText = null;
        if(idCharArray.length < EVENTID_COUNT){
            ramdomText = getRamdomText(EVENTID_COUNT - idCharArray.length);
        }
        return new String(idCharArray) + (ramdomText!=null ? ramdomText : "");
    }


    public static char compare(char a, char b){
        if (a == b){
            a = getReplaceChar();
            return compare(a,b);
        }else {
            return a;
        }
    }
    public static char getReplaceChar(){
        replaceChar = (char)(replaceChar + 1);
        // 目前只能添加1-5的数字
        if(replaceChar > '5'){
            replaceChar = '1';
        }
        return replaceChar;
    }

    public static String getRamdomText(int count){
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        while (count > 0) {
            int x = (int) (Math.random() * 5 + 1);
            if (Math.abs(x - pre) > 0) {
                sb.append(x);
                --count;
                pre = x;
            }
        }

        return sb.toString();
    }
}
