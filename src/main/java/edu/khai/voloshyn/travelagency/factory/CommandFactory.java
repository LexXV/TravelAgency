package edu.khai.voloshyn.travelagency.factory;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.CommandType;
import edu.khai.voloshyn.travelagency.command.impl.ErrorCommand;
import edu.khai.voloshyn.travelagency.command.impl.HomeCommand;
import edu.khai.voloshyn.travelagency.command.impl.redirect.RedirectCommand;
import edu.khai.voloshyn.travelagency.command.impl.redirect.RedirectToInputTourNumberPageCommand;
import edu.khai.voloshyn.travelagency.command.impl.redirect.RedirectToSubmitBuyPageCommand;
import edu.khai.voloshyn.travelagency.command.impl.redirect.RedirectToUpdateTourPageCommand;
import edu.khai.voloshyn.travelagency.command.impl.tour.*;
import edu.khai.voloshyn.travelagency.command.impl.user.*;

public class CommandFactory {

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return CommandHolder.COMMAND_HOLDER_INSTANCE;
    }

    public Command getCommand(String typeCommand) {
        if (typeCommand == null) {
            return new HomeCommand();
        }
        CommandType type = CommandType.valueOf(typeCommand.toUpperCase());
        switch (type) {
            case SIGN_IN:
                return new SignInCommand();
            case SIGN_UP:
                return new SignUpCommand();
            case SIGN_OUT:
                return new SignOutCommand();
            case HOME:
                return new HomeCommand();
            case BLOCK_CLIENT:
                return new BlockClientCommand();
            case UNBLOCK_CLIENT:
                return new UnblockClientCommand();
            case GET_USERS_LIST:
                return new GetUsersListCommand();
            case CREATE_ADMIN:
                return new CreateAdminCommand();
            case UPDATE_ADMIN:
                return new UpdateAdminCommand();
            case CREATE_MANAGER:
                return new CreateManagerCommand();
            case UPDATE_MANAGER:
                return new UpdateManagerCommand();
            case UPDATE_CLIENT:
                return new UpdateClientCommand();
            case DELETE_CLIENT:
                return new DeleteClientCommand();
            case GET_TOURS_LIST:
                return new GetToursListCommand();
            case GET_HOT_TOURS:
                return new GetHotToursCommand();
            case UN_HOT_TOUR:
                return new UnHotTourCommand();
            case SET_HOT_TOUR:
                return new SetHotTourCommand();
            case DELETE_TOUR:
                return new DeleteTourCommand();
            case GET_BY_CITY:
                return new GetToursByCity();
            case GET_BY_HOTEL:
                return new GetToursByHotel();
            case GET_BY_TOURIST:
                return new GetToursByTourist();
            case GET_BY_TOUR_TYPE:
                return new GetToursByType();
            case ADD_TOUR:
                return new AddTourCommand();
            case UPDATE_TOUR:
                return new UpdateTourCommand();
            case REDIRECT_TO_UPDATE_TOUR_PAGE:
                return new RedirectToUpdateTourPageCommand();
            case SEARCH_TOURS:
                return new SearchToursByParameters();
            case REDIRECT_TO_INPUT_TOUR_NUMBER_PAGE:
                return new RedirectToInputTourNumberPageCommand();
            case BUY_TOUR:
                return new BuyTourCommand();
            case CANCEL_BUYING_TOUR:
                return new CancelBuyingTourCommand();
            case SET_BOUGHT_ORDER:
                return new SetBoughtOrderCommand();
            case REDIRECT_TO_SUBMIT_BUY_PAGE:
                return new RedirectToSubmitBuyPageCommand();
            case GET_ORDERS:
                return new GetAllOrdersCommand();
            case GET_ORDERS_BY_USER_ID:
                return new GetOrdersByUserIdCommand();
            case DELETE_CITY:
                return new DeleteCityCommand();
            case DELETE_HOTEL:
                return new DeleteHotelCommand();
            case DELETE_TOURIST:
                return new DeleteTouristCommand();
            case DELETE_TOUR_TYPE:
                return new DeleteTourTypeCommand();
            case ADD_CITY:
                return new AddCityCommand();
            case ADD_HOTEL:
                return new AddHotelCommand();
            case ADD_TOURIST:
                return new AddTouristCommand();
            case ADD_TOUR_TYPE:
                return new AddTourTypeCommand();
            case REDIRECT:
                return new RedirectCommand();
            default:
                return new ErrorCommand();
        }
    }

    private static class CommandHolder {
        public static final CommandFactory COMMAND_HOLDER_INSTANCE = new CommandFactory();
    }
}
