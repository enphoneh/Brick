package com.aven.brick.core;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * @author : Aven
 * @date :  [2018-05-31]
 */
public class ActivityStarter {

    private static final String EXTRA_OLD_CN = "extra_old_cn";

    public static void startActivity(Context context, Intent intent) {
        ComponentName cn = intent.getComponent();
        if (cn == null) {
            // Action Intent, do not modify intent
            startActivityInner(context, intent);
            return;
        }

        ComponentName newCn = new ComponentName(context.getPackageName(), "com.aven.brick.core.subActivity");
        intent.setComponent(newCn);
        intent.putExtra(EXTRA_OLD_CN, cn);
        startActivityInner(context, intent);
    }

    public static String handleIntent(Intent intent) {
        ComponentName cn = intent.getParcelableExtra(EXTRA_OLD_CN);
        if (cn == null) {
            return null;
        }
        intent.setComponent(cn);
        return cn.getClassName();
    }

    public static void replaceWithStub(Context context, Intent intent) {
        ComponentName cn = intent.getComponent();
        if (cn == null) {
            // Action Intent, do not modify intent
            return;
        }

        ComponentName newCn = new ComponentName(context.getPackageName(), "com.aven.brick.core.subActivity");
        intent.setComponent(newCn);
        intent.putExtra(EXTRA_OLD_CN, cn);
    }

    private static void startActivityInner(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
