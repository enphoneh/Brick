package com.aven.brick.core;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * @author : Aven
 * @date :  [2018-05-31]
 */
public class InstrumentationWrapper extends Instrumentation {

    Instrumentation mDelegate;

    public InstrumentationWrapper(Instrumentation instrumentation) {
        mDelegate = instrumentation;
    }

    @Override
    public void onCreate(Bundle arguments) {
        mDelegate.onCreate(arguments);
    }

    @Override
    public void start() {
        mDelegate.start();
    }

    @Override
    public void onStart() {
        mDelegate.onStart();
    }

    @Override
    public boolean onException(Object obj, Throwable e) {
        return mDelegate.onException(obj, e);
    }

    @Override
    public void sendStatus(int resultCode, Bundle results) {
        mDelegate.sendStatus(resultCode, results);
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        mDelegate.finish(resultCode, results);
    }

    @Override
    public void setAutomaticPerformanceSnapshots() {
        mDelegate.setAutomaticPerformanceSnapshots();
    }

    @Override
    public void startPerformanceSnapshot() {
        mDelegate.startPerformanceSnapshot();
    }

    @Override
    public void endPerformanceSnapshot() {
        mDelegate.endPerformanceSnapshot();
    }

    @Override
    public void onDestroy() {
        mDelegate.onDestroy();
    }

    @Override
    public Context getContext() {
        return mDelegate.getContext();
    }

    @Override
    public ComponentName getComponentName() {
        return mDelegate.getComponentName();
    }

    @Override
    public Context getTargetContext() {
        return mDelegate.getTargetContext();
    }

    @Override
    public boolean isProfiling() {
        return mDelegate.isProfiling();
    }

    @Override
    public void startProfiling() {
        mDelegate.startProfiling();
    }

    @Override
    public void stopProfiling() {
        mDelegate.stopProfiling();
    }

    @Override
    public void setInTouchMode(boolean inTouch) {
        mDelegate.setInTouchMode(inTouch);
    }

    @Override
    public void waitForIdle(Runnable recipient) {
        mDelegate.waitForIdle(recipient);
    }

    @Override
    public void waitForIdleSync() {
        mDelegate.waitForIdleSync();
    }

    @Override
    public void runOnMainSync(Runnable runner) {
        mDelegate.runOnMainSync(runner);
    }

    @Override
    public Activity startActivitySync(Intent intent) {
        return mDelegate.startActivitySync(intent);
    }

    @Override
    public void addMonitor(ActivityMonitor monitor) {
        mDelegate.addMonitor(monitor);
    }

    @Override
    public ActivityMonitor addMonitor(IntentFilter filter,
                                      ActivityResult result,
                                      boolean block) {
        return mDelegate.addMonitor(filter, result, block);
    }

    @Override
    public ActivityMonitor addMonitor(String cls,
                                      ActivityResult result,
                                      boolean block) {
        return mDelegate.addMonitor(cls, result, block);
    }

    @Override
    public boolean checkMonitorHit(ActivityMonitor monitor, int minHits) {
        return mDelegate.checkMonitorHit(monitor, minHits);
    }

    @Override
    public Activity waitForMonitor(ActivityMonitor monitor) {
        return mDelegate.waitForMonitor(monitor);
    }

    @Override
    public Activity waitForMonitorWithTimeout(ActivityMonitor monitor,
                                              long timeOut) {
        return mDelegate.waitForMonitorWithTimeout(monitor, timeOut);
    }

    @Override
    public void removeMonitor(ActivityMonitor monitor) {
        mDelegate.removeMonitor(monitor);
    }

    @Override
    public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag) {
        return mDelegate.invokeMenuActionSync(targetActivity, id, flag);
    }

    @Override
    public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag) {
        return mDelegate.invokeContextMenuAction(targetActivity, id, flag);
    }

    @Override
    public void sendStringSync(String text) {
        mDelegate.sendStringSync(text);
    }

    @Override
    public void sendKeySync(KeyEvent event) {
        mDelegate.sendKeySync(event);
    }

    @Override
    public void sendKeyDownUpSync(int key) {
        mDelegate.sendKeyDownUpSync(key);
    }

    @Override
    public void sendCharacterSync(int keyCode) {
        mDelegate.sendCharacterSync(keyCode);
    }

    @Override
    public void sendPointerSync(MotionEvent event) {
        mDelegate.sendPointerSync(event);
    }

    @Override
    public void sendTrackballEventSync(MotionEvent event) {
        mDelegate.sendTrackballEventSync(event);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return mDelegate.newApplication(cl, className, context);
    }

    @Override
    public void callApplicationOnCreate(Application app) {
        mDelegate.callApplicationOnCreate(app);
    }

    @Override
    public Activity newActivity(Class<?> clazz, Context context, IBinder token,
                                Application application, Intent intent,
                                ActivityInfo info, CharSequence title,
                                Activity parent, String id,
                                Object lastNonConfigurationInstance)
        throws InstantiationException, IllegalAccessException {
        //className = ActivityStarter.handleIntent(intent);
        return mDelegate.newActivity(clazz, context, token, application, intent, info, title, parent, id,
            lastNonConfigurationInstance);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String newClass = ActivityStarter.handleIntent(intent);
        if (!TextUtils.isEmpty(newClass)) {
            className = newClass;
        }
        return mDelegate.newActivity(cl, className, intent);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        mDelegate.callActivityOnCreate(activity, icicle);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle,
                                     PersistableBundle persistentState) {
        mDelegate.callActivityOnCreate(activity, icicle, persistentState);
    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        mDelegate.callActivityOnDestroy(activity);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        mDelegate.callActivityOnRestoreInstanceState(activity, savedInstanceState);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState,
                                                   PersistableBundle persistentState) {
        mDelegate.callActivityOnRestoreInstanceState(activity, savedInstanceState, persistentState);
    }

    @Override
    public void callActivityOnPostCreate(Activity activity, Bundle icicle) {
        mDelegate.callActivityOnPostCreate(activity, icicle);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Override
    public void callActivityOnPostCreate(Activity activity, Bundle icicle,
                                         PersistableBundle persistentState) {
        mDelegate.callActivityOnPostCreate(activity, icicle, persistentState);
    }

    @Override
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        mDelegate.callActivityOnNewIntent(activity, intent);
    }

    @Override
    public void callActivityOnStart(Activity activity) {
        mDelegate.callActivityOnStart(activity);
    }

    @Override
    public void callActivityOnRestart(Activity activity) {
        mDelegate.callActivityOnRestart(activity);
    }

    @Override
    public void callActivityOnResume(Activity activity) {
        mDelegate.callActivityOnResume(activity);
    }

    @Override
    public void callActivityOnStop(Activity activity) {
        mDelegate.callActivityOnStop(activity);
    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        mDelegate.callActivityOnSaveInstanceState(activity, outState);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState,
                                                PersistableBundle outPersistentState) {
        mDelegate.callActivityOnSaveInstanceState(activity, outState, outPersistentState);
    }

    @Override
    public void callActivityOnPause(Activity activity) {
        mDelegate.callActivityOnPause(activity);
    }

    @Override
    public void callActivityOnUserLeaving(Activity activity) {
        mDelegate.callActivityOnUserLeaving(activity);
    }

    @Override
    public void startAllocCounting() {
        mDelegate.startAllocCounting();
    }

    @Override
    public void stopAllocCounting() {
        mDelegate.stopAllocCounting();
    }

    @Override
    public Bundle getAllocCounts() {
        return mDelegate.getAllocCounts();
    }

    @Override
    public Bundle getBinderCounts() {
        return mDelegate.getBinderCounts();
    }

    @TargetApi(VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public UiAutomation getUiAutomation() {
        return mDelegate.getUiAutomation();
    }


}
