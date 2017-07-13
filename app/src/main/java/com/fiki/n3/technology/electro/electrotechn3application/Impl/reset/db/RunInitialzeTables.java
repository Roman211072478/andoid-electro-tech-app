package com.fiki.n3.technology.electro.electrotechn3application.Impl.reset.db;

import android.content.Context;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.ContactDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.EquationDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.LoginDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.MachineEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.PronyBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RoleDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RopeBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.StaffDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.SwineburneDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TorqueDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TutorialDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.UserDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.reset.db.init.BackEmfInit;

/**
 * Created by Roman on 2016/06/15.
 */
public class RunInitialzeTables {

    private BackEmfInit backEmfDAO;
    private ContactDetailsDAO contactDetailsDAO;
    private EquationDAO equationDAO;
    private LoginDetailsDAO loginDetailsDAO;
    private MachineEmfDAO machineEmfDAO;
    private PronyBreakDAO pronyBreakDAO;
    private RoleDAO roleDAO;
    private RopeBreakDAO ropeBreakDAO;
    private StaffDAO staffDAO;
    private SwineburneDAO swineburneDAO;
    private TorqueDAO torqueDAO;
    private TutorialDAO tutorialDAO;
    private UserDAO userDAO;


    public RunInitialzeTables(Context ctx)
    {
        setUp(ctx);

    }

    private void setUp(Context ctx)
    {
          backEmfDAO = new  BackEmfInit(ctx);
    }
    public boolean runBackEmf()
    {
        boolean passed = backEmfDAO.startInit();
        return passed;
    }
}
