package com.aven.brick.core;

import java.net.URL;
import java.util.Enumeration;

import com.aven.brick.utils.Reflector;
import dalvik.system.PathClassLoader;

/**
 * @author : Aven
 * @date :  [2018-05-31]
 */
public class ProxyClassLoader extends PathClassLoader {

    ClassLoader mOriginClassLoader;

    public ProxyClassLoader(ClassLoader parent, ClassLoader origin) {
        super("", "", parent);
        mOriginClassLoader = origin;
        syncData(origin);
    }

    private void syncData(ClassLoader origin) {
        // sync path list to local
        Object pathList = Reflector.on(origin).get("pathList");
        Reflector.on(this).set("pathList", pathList);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected URL findResource(String resName) {
        try {
            return (URL) Reflector.on(mOriginClassLoader).call("findResource", resName).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findResource(resName);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Enumeration<URL> findResources(String resName) {
        try {
            return (Enumeration<URL>) Reflector.on(mOriginClassLoader).call("findResources", resName).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findResources(resName);
    }

    @Override
    public String findLibrary(String libName) {
        try {
            return (String) Reflector.on(mOriginClassLoader).call("findLibraryMethod", libName).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findLibrary(libName);
    }

    @Override
    protected Package getPackage(String name) {
        // from Qihoo360 RePlugin .............
        // 金立手机的某些ROM(F103,F103L,F303,M3)代码ClassLoader.getPackage去掉了关键的保护和错误处理(2015.11~2015.12左右)，会返回null
        // 悬浮窗某些draw代码触发getPackage(...).getName()，getName出现空指针解引，导致悬浮窗进程出现了大量崩溃
        // 此处实现和AOSP一致确保不会返回null
        // SONGZHAOCHUN, 2016/02/29
        if (name != null && !name.isEmpty()) {
            Package pack = null;
            try {
                pack = (Package) Reflector.on(mOriginClassLoader).call("getPackage", name).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (pack == null) {
                pack = super.getPackage(name);
            }
            if (pack == null) {
                return definePackage(name, "Unknown", "0.0", "Unknown", "Unknown", "0.0", "Unknown", null);
            }
            return pack;
        }
        return null;
    }

}
