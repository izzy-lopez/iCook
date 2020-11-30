package iCook.Model;
import iCook.Model.DatabaseAccess.IngredientDAO;
import iCook.Model.DatabaseAccess.RecipeDAO;
import iCook.Model.DatabaseAccess.UserDAO;
import iCook.UsernameTakenException;

import java.util.ArrayList;

/**
 * Central class for all DAO objects. Has access to all DAO objects.
 *
 * @author Team 2
 * @version 11/28/2020
 */
public class Facade {

    // instance variables (these are not unique for each object)
    private static final UserDAO userDAO = new UserDAO();
    private static final IngredientDAO ingredientDAO = new IngredientDAO();
    private static final RecipeDAO recipeDAO = new RecipeDAO();

    /**
     * Constructor - does nothing.
     */
    public Facade() {
    }


    /**
     * Calls the UserDAO to determine if the user's credentials are valid and
     * returns true if so, false otherwise.
     *
     * If true, we request the userDAO to create the user singleton
     */
    public boolean login(String username, String password)
    {
        // determine if the user's login info is valid, if so return true, false otherwise
        if ( userDAO.validUserLogin(username, password) )
        {
            userDAO.getUser(username, password);
            return true;
        }
        else
            return false;
    }


    /**
     * Calls the UserDAO to create a new user with the given credentials
     *
     * If the username isn't taken, we request the userDAO to create the user singleton
     */
    public void signUp(String username, String password)
    {
        // make sure the username isn't taken
        // NEED TO THROW AN EXCEPTION HERE
        if ( userDAO.usernameIsTaken(username) ) {
            System.out.println("UsernameTakenException thrown");
            throw new UsernameTakenException("\"" + username + "\"" + " is already in use.");
        }
        else {
            // create a new User with the given username and password
            userDAO.addUser(username, password);
            userDAO.getUser(username, password);
        }
    }


    /**
     * Calls the IngredientDAO to return an ArrayList of Ingredient objects (all system objects)
     */
    public ArrayList<Ingredient> getSystemIngredients()
    {
        return ingredientDAO.getAllIngredients();
    }


    /**
     * Calls the UserDAO to return an ArrayList of UserIngredient objects (all user's ingredients)
     */
    public ArrayList<UserIngredient> getUserIngredients(int userID)
    {
        return userDAO.getUserIngredients(userID);
    }

} // end of Facade class