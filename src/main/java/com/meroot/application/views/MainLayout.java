package com.meroot.application.views;


import com.meroot.application.components.appnav.AppNav;
import com.meroot.application.components.appnav.AppNavItem;
import com.meroot.application.views.about.AboutView;
import com.meroot.application.views.addressform.AddressFormView;
import com.meroot.application.views.collaborativemasterdetail.CollaborativeMasterDetailView;
import com.meroot.application.views.dashboard.DashboardView;
import com.meroot.application.views.empty.EmptyView;
import com.meroot.application.views.imagelist.ImageListView;
import com.meroot.application.views.list.ListView;
import com.meroot.application.views.personform.PersonFormView;
import com.meroot.application.views.richtexteditor.RichTextEditorView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.router.PageTitle;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("view-title");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("view-header");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("VaadinApp");
        appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private AppNav createNavigation() {
        AppNav nav = new AppNav();
        nav.addClassNames("app-nav");

        nav.addItem(new AppNavItem("Dashboard", DashboardView.class, "la la-chart-area"));
        nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));
        nav.addItem(new AppNavItem("Person Form", PersonFormView.class, "la la-user"));
        nav.addItem(new AppNavItem("Address Form", AddressFormView.class, "la la-map-marker"));
        nav.addItem(
                new AppNavItem("Collaborative Master-Detail", CollaborativeMasterDetailView.class, "la la-columns"));
        nav.addItem(new AppNavItem("Rich Text Editor", RichTextEditorView.class, "la la-edit"));
        nav.addItem(new AppNavItem("Image List", ImageListView.class, "la la-th-list"));
        nav.addItem(new AppNavItem("List", ListView.class, "la la-th"));
        nav.addItem(new AppNavItem("Empty", EmptyView.class, "la la-file"));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("app-nav-footer");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
