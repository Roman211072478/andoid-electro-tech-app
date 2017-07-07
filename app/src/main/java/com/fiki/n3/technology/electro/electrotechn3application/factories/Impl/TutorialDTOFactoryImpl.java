package com.fiki.n3.technology.electro.electrotechn3application.factories.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;
import com.fiki.n3.technology.electro.electrotechn3application.factories.TutorialDTOFactory;

/**
 * Created by Roman on 2016/04/17.
 */
public class TutorialDTOFactoryImpl implements TutorialDTOFactory {
    private static TutorialDTOFactoryImpl factory = null;

    private  TutorialDTOFactoryImpl() {
    }

    public static TutorialDTOFactoryImpl getInstance(){
        if(factory ==null)
            factory = new TutorialDTOFactoryImpl();
        return factory;
    }


    @Override
    public TutorialDTO createTutorialDTO(Integer staffId, String screenId, Integer equationId, Integer tutorialId) {
        TutorialDTO dto = new TutorialDTO.Builder()
                .staffId(staffId)
                .screenId(screenId)
                .equationId(equationId)
                .TutorialId(tutorialId)
                .build();

        return dto;
    }
}
