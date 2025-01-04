package com.sample.constant;

import java.util.HashMap;
import java.util.Map;

import com.sample.service.impl.CatActions;
import com.sample.service.impl.DogActions;
import com.sample.service.interfaces.Animals;

public enum ActionsEnum {
	CAT("CAT", CatActions.class),
	DOG("DOG", DogActions.class);

	private static final Map<String, ActionsEnum> animalActionsMap = new HashMap<>();
	

    static {
        for (ActionsEnum action : values()) {
            animalActionsMap.put(action.name, action);
        }
    }
    
    public Class<? extends Animals> getAnimalsActionClass() {
    	return animalsActionClass;
    }

    public static Class<? extends Animals> getActionClassByName(String name) {
    	ActionsEnum action = animalActionsMap.get(name);
        return action.getAnimalsActionClass();
    }
	
	private String name;
	private Class<? extends Animals> animalsActionClass;
	
	private ActionsEnum(String name, Class<? extends Animals> animalsActionClass) {
		this.name=name;
		this.animalsActionClass=animalsActionClass;
	}
}
