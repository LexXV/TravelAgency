package edu.khai.voloshyn.travelagency.command.constants;

/**
 * The enum Page Type.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public enum PageType {
    HOME_PAGE("/WEB-INF/view/homePage.jsp"),
    SIGN_IN_PAGE("/WEB-INF/view/signIn.jsp"),
    SIGN_UP_PAGE("/WEB-INF/view/signUp.jsp"),
    ERROR_PAGE("/WEB-INF/view/error.jsp"),
    REPEAT_SIGN_IN_PAGE("/WEB-INF/view/repeatSignIn.jsp"),
    ABOUT_PAGE("/WEB-INF/view/about.jsp"),
    SEARCH_PAGE("/WEB-INF/view/search.jsp"),
    CONTACT_PAGE("/WEB-INF/view/contact.jsp"),
    USERS_LIST_PAGE("/WEB-INF/view/usersList.jsp"),
    CREATE_ADMIN_PAGE("/WEB-INF/view/createAdmin.jsp"),
    CREATE_MANAGER_PAGE("/WEB-INF/view/createManager.jsp"),
    UPDATE_USER_PAGE("/WEB-INF/view/updateUser.jsp"),
    USER_INFO_PAGE("/WEB-INF/view/userInfo.jsp"),
    TOURS_LIST_PAGE("/WEB-INF/view/toursList.jsp"),
    ADD_TOUR_PAGE("/WEB-INF/view/addTour.jsp"),
    UPDATE_TOUR_PAGE("/WEB-INF/view/updateTour.jsp"),
    INPUT_TOUR_NUMBER_PAGE("/WEB-INF/view/inputTourNumber.jsp"),
    SUBMIT_BUY_PAGE("/WEB-INF/view/submitBuy.jsp"),
    ORDERS_LIST_PAGE("/WEB-INF/view/ordersList.jsp"),
    NO_MONEY_PAGE("/WEB-INF/view/noMoney.jsp"),
    SERVICE_PAGE("/WEB-INF/view/service.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getAddress() {
        return value;
    }
}
