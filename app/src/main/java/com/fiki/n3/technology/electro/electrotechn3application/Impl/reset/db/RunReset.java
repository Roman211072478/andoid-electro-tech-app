package com.fiki.n3.technology.electro.electrotechn3application.Impl.reset.db;

import android.content.Context;

import com.fiki.roman.andoirdmathlab.repository.Impl.BackEmfDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.ContactDetailsDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.EquationDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.BackEmfDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.ContactDetailsDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.EquationDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.LoginDetailsDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.MachineEmfDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.PronyBreakDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.RoleDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.RopeBreakDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.StaffDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.SwineburneDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.TorqueDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.TutorialDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.UserDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.LoginDetailsDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.MachineEmfDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.PronyBreakDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.RoleDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.RopeBreakDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.StaffDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.SwineburneDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.TorqueDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.TutorialDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.UserDAO;

/**
 * Created by Roman on 2016/06/15.
 */
public class RunReset {

    private BackEmfDAO backEmfDAO;
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


    private boolean succeed;

    /* default Constructor*/
    public RunReset(Context context)
    {
        setUp(context);
        succeed = true;
    }


    public boolean reset()
    {
        try {
            backEmfDAO.deleteTable();//1
            contactDetailsDAO.deleteTable();//2
            equationDAO.deleteTable();//3
            loginDetailsDAO.deleteTable();//4
            machineEmfDAO.deleteTable();//5
            pronyBreakDAO.deleteTable();//6
            roleDAO.deleteTable();//7
            ropeBreakDAO.deleteTable();//8
            staffDAO.deleteTable();//9
            swineburneDAO.deleteTable();//10
            torqueDAO.deleteTable();//11
            tutorialDAO.deleteTable();//12
            userDAO.deleteTable();//13
        }
        catch(Exception e)
        {
            succeed = false;
        }
        return succeed;

    }




    /*Set up the DAO*/
    private void setUp(Context ctx)
    {
          backEmfDAO = new BackEmfDAOImpl(ctx);
          contactDetailsDAO = new ContactDetailsDAOImpl(ctx);
          equationDAO = new EquationDAOImpl(ctx);
          loginDetailsDAO = new LoginDetailsDAOImpl(ctx);
          machineEmfDAO = new MachineEmfDAOImpl(ctx);
          pronyBreakDAO = new PronyBreakDAOImpl(ctx);
          roleDAO = new RoleDAOImpl(ctx);
          ropeBreakDAO = new RopeBreakDAOImpl(ctx);
          staffDAO = new StaffDAOImpl(ctx);
          swineburneDAO = new SwineburneDAOImpl(ctx);
          torqueDAO = new TorqueDAOImpl(ctx);
          tutorialDAO = new TutorialDAOImpl(ctx);
          userDAO = new UserDAOImpl(ctx);
    }
}
