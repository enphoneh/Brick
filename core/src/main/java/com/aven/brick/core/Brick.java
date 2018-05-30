package com.aven.brick.core;

import android.app.ActivityThread;
import android.app.Instrumentation;
import android.content.Context;
import com.aven.brick.utils.Reflector;

/**
 * Brick core entry
 * @author : Aven
 * @date :  [2018-05-31]
 */
public class Brick {

    public static void attachBaseContext(Context context){
        Instrumentation instrumentation = Reflector.on(ActivityThread.currentActivityThread()).get("mInstrumentation");
        Reflector.on(ActivityThread.currentActivityThread()).set("mInstrumentation", new InstrumentationWrapper(instrumentation));
    }
}
