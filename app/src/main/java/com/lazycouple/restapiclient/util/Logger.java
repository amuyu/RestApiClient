package com.lazycouple.restapiclient.util;

import android.util.Log;

public class Logger {
    
    public static boolean willLogShow = true;
    
    public static void e(String tag, String Message) {
        if(willLogShow) {
            Log.e(tag, Message);
        }
    }
    
    public static void e(String tag, String className, String Message) {
        if(willLogShow) {
            Log.e(tag, className + " ::::: " + Message);
        }
    }
    
    public static void d(String tag, String Message) {
        if(willLogShow) {
            Log.d(tag, Message);
        }
    }
    
    public static void d(String tag, String className, String Message) {
        if(willLogShow) {
            Log.e(tag, className + " ::::: " + Message);
        }
    }
    
    public static void v(String tag, String Message) {
        if(willLogShow) {
            Log.v(tag, Message);
        }
    }
    
    public static void w(String tag, String Message) {
        if(willLogShow) {
            Log.w(tag, Message);
        }
    }
    
    public static void i(String tag, String Message) {
        if(willLogShow) {
            Log.i(tag, Message);
        }
    }
    
    public static void i(String tag, String className, String Message) {
        if(willLogShow) {
            Log.e(tag, className + " ::::: " + Message);
        }
    }
    
    public static void error(Exception e){
    	if(willLogShow) {
            e.printStackTrace();
        }
    }
    
    public static void error(Error e) {
    	if(willLogShow) {
    		e.printStackTrace();
    	}
    }
    
//    public static void error(FacebookError e) {
//    	if(willLogShow) {
//    		e.printStackTrace();
//    	}
//    }
    
    public static void setLog(String classname, String tag, String msg){
		if(willLogShow){
			Log.v(tag, classname+", "+msg);
		}
	}
    
//    public static void sendError(Context context, Exception e , String comment , String errorType){
//    	try {
//			ApiCalls.sendErrorLog(context, e , comment , errorType);
//		} catch (Exception e1) {
//			Utils.getResultFromPostUrl(Constants.API_ERROR_LOG, Constants.HTTP_POST, null);
//		}
//    }

}
