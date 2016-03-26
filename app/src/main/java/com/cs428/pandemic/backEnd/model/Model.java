package com.cs428.pandemic.backEnd.model;

/**
 * Created by brandt on 3/23/16.
 */
public class Model {

    private static IGameModel instance = null;

    public static IGameModel getInstance(){
        // TODO: James implement this John
        if(instance == null) {
            IGameModelFactory fact = new CompositeModelFactory();
            instance = fact.createModelBuilder().createModel();
        }
        return instance;
    }


}
