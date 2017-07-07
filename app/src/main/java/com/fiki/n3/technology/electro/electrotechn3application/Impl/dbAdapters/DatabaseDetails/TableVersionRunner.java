package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails;

import android.content.Context;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TableVersionDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TableVersionDAO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TableVersionDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/06/15.
 */
public class TableVersionRunner {

    private TableVersionDAO dao;

    public TableVersionRunner(Context ctx)
    {
        dao = new TableVersionDAOImpl(ctx);
    }

    public Long createDefault(){
        Long number;
        TableVersionDTO object = null;

        TableVersionDTO dto = new TableVersionDTO.Builder()
                .version(0)
                .build();

             object = findById("version");

            if(object.getVersion() == -1)
            {
                number = dao.create(dto);
            }
        else
            {
                number = new Long(-1);
            }

        return number;
    }

    public int update(TableVersionDTO dto){

        int result = dao.update(dto);

        return result;
    }
    public int delete(Integer id){
        int result = dao.delete(id);
        return result;
    }
    public int deleteTable(){
        int result = dao.deleteTable();
        return result;
    }
    public TableVersionDTO findById(String type){
        TableVersionDTO dto = dao.findById(type);


        return dto;
    }
    public HashMap getList(){
        HashMap map = dao.getList();

        return map;
    }

}
