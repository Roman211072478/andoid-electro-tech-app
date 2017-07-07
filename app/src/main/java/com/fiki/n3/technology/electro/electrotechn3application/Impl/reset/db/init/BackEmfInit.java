package com.fiki.n3.technology.electro.electrotechn3application.Impl.reset.db.init;

import android.content.Context;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.math.lab.mathlab.factories.BackEmfFactory;
import com.fiki.math.lab.mathlab.factories.Impl.BackEmfFactoryImpl;
import com.fiki.roman.andoirdmathlab.dto.TableVersionDTO;
import com.fiki.roman.andoirdmathlab.repository.Impl.BackEmfDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.Impl.BackEmfDAOImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.dbAdapters.DatabaseDetails.TableVersionRunner;

import java.util.HashMap;

/**
 * Created by Roman on 2016/06/15.
 */
public class BackEmfInit {
    private BackEmfDAO dao;
    private BackEmfFactory factory;
    private Context ctx;
    private TableVersionRunner runner;

    public BackEmfInit(Context ctx)
    {
        this.ctx = ctx;
    }

    //openDB
    private void setUp(){
        dao  = new BackEmfDAOImpl(ctx);
        factory = BackEmfFactoryImpl.getInstance();
        runner = new TableVersionRunner(ctx);
    }

    public boolean startInit() {

        try {
            //Create Object
            BackEmf backEmf = factory.createBackEmf(1, 2, 3, 4);

            //create
            Long resultId = dao.create(backEmf, 12);//returns primary key or -1 for unsuccessFul

            if(resultId != -1L) {
                HashMap map = new HashMap();
                //Cursor map;

                //get all rows
                map = dao.getList();

                //find by id
                Integer id = resultId.intValue();

                BackEmf backEmfRetunred = dao.findById(id);

                dao.delete(id);

            }
            else {

                Long returnValue = runner.createDefault();
                if(returnValue == -1L)
                {
                    TableVersionDTO dto = runner.findById("version");
                    Integer version = dto.getVersion();
                    version = version + 1;

                    dto = new TableVersionDTO.Builder()
                            .copy(dto)
                            .version(version)
                            .build();

                    runner.update(dto);

                    startInit();
                }
            }
        }
        catch(Exception e)
        {
            return false;
        }
        tearDown();//release memory
        return true;
    }
    /*Release memory*/
    private void tearDown() {
        dao = null;
        dao = null;
        factory = null;
    }
}
