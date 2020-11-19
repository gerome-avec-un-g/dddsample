package fr.geromeavecung.dddsample.books;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class NoCircularViewPathTestViewResolver extends InternalResourceViewResolver {

    /**
     * prevent checking for circular view paths caused by Thymeleaf views
     * having the same name as controller url
     */
    @Override
    protected AbstractUrlBasedView buildView(final String viewName) throws Exception {
        final InternalResourceView view = (InternalResourceView) super.buildView(viewName);

        view.setPreventDispatchLoop(false);
        return view;
    }
}