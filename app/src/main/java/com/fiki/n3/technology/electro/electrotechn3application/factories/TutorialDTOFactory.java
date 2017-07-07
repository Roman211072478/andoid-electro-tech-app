package com.fiki.n3.technology.electro.electrotechn3application.factories;


import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

/**
 * Created by Roman on 2016/04/17.
 */
public interface TutorialDTOFactory {

    TutorialDTO createTutorialDTO(Integer staffId,
                                  String screenId,
                                  Integer equationId,
                                  Integer TutorialId);
}
