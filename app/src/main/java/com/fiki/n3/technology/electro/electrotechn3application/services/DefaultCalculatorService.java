package com.fiki.n3.technology.electro.electrotechn3application.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DefaultCalculatorService extends IntentService {



    public DefaultCalculatorService() {
        super("Default calc");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {

            ApplicationInfo info = getPackageManager()
                    .getApplicationInfo("com.android.calculator2",0);

            ArrayList<HashMap<String, Object>> Items = new ArrayList<HashMap<String, Object>>();
            //PackageManager pm;
            final PackageManager pm = getPackageManager();
            List<PackageInfo> packs = pm.getInstalledPackages(0);
            for (PackageInfo pi : packs) {
                if (pi.packageName.toString().toLowerCase().contains("calcul")) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("appName", pi.applicationInfo.loadLabel(pm));
                    map.put("packageName", pi.packageName);
                    Items.add(map);
                }
            }

            if (Items.size() >= 1) {
                String packageName = (String) Items.get(0).get("packageName");
                Intent i = pm.getLaunchIntentForPackage(packageName);
                if (i != null) {
                    startActivity(i);
                }

            }
        }
        catch(PackageManager.NameNotFoundException e)
        {

        }
    }
}
