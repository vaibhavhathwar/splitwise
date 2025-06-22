import {
  CircleUser,
  CreditCard,
  Download,
  Home,
  User,
  Users,
} from "lucide-react";

import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarProvider,
  SidebarTrigger,
} from "@/components/ui/sidebar";

const AppSideBar = () => {
  const menuItems = [
    {
      title: "Home",
      url: "/",
      icon: Home,
    },
    {
      title: "Friends",
      url: "/friends",
      icon: User,
    },
    {
      title: "Groups",
      url: "groups",
      icon: Users,
    },
    {
      title: "Expenenses",
      url: "#",
      icon: CreditCard,
    },
    {
      title: "Export",
      url: "#",
      icon: Download,
    },
  ];

  const groupsList = [
    {
      title: "Gamer Bros",
      url: "#",
      icon: CircleUser,
    },
    {
      title: "Andaman Trip",
      url: "#",
      icon: CircleUser,
    },
    {
      title: "Birthday celecbration",
      url: "#",
      icon: CircleUser,
    },
    {
      title: "Hawaii Trip",
      url: "#",
      icon: CircleUser,
    },
  ];
  const friendsList = [
    {
      title: "John",
      url: "#",
      icon: CircleUser,
    },
    {
      title: "Marry",
      url: "#",
      icon: CircleUser,
    },
    {
      title: "Luke",
      url: "#",
      icon: CircleUser,
    },
    {
      title: "Ravi",
      url: "#",
      icon: CircleUser,
    },
  ];
  return (
    <SidebarProvider className="w-fit">
      <Sidebar variant="floating" collapsible="icon">
        <SidebarContent>
          <SidebarTrigger className="p-6" />
          <SidebarMenu>
            {menuItems.map((item) => (
              <SidebarMenuItem key={item.title}>
                <SidebarMenuButton asChild>
                  <a href={item.url}>
                    <item.icon />
                    <span>{item.title}</span>
                  </a>
                </SidebarMenuButton>
              </SidebarMenuItem>
            ))}
          </SidebarMenu>
          <SidebarGroup>
            <SidebarGroupLabel>My Groups</SidebarGroupLabel>
            <SidebarGroupContent>
              <SidebarMenu>
                {groupsList.map((item) => (
                  <SidebarMenuItem key={item.title}>
                    <SidebarMenuButton asChild>
                      <a href={item.url}>
                        <item.icon />
                        <span>{item.title}</span>
                      </a>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                ))}
              </SidebarMenu>
            </SidebarGroupContent>
          </SidebarGroup>
          <SidebarGroup>
            <SidebarGroupLabel>My Friends</SidebarGroupLabel>
            <SidebarGroupContent>
              <SidebarMenu>
                {friendsList.map((item) => (
                  <SidebarMenuItem key={item.title}>
                    <SidebarMenuButton asChild>
                      <a href={item.url}>
                        <item.icon />
                        <span>{item.title}</span>
                      </a>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                ))}
              </SidebarMenu>
            </SidebarGroupContent>
          </SidebarGroup>
          <SidebarFooter className="p-0 border-t">
            <SidebarMenu>
              <SidebarMenuItem>
                <SidebarMenuButton asChild>
                  <a href="#">
                    <User />
                    <span>{"Vaibhav"}</span>
                  </a>
                </SidebarMenuButton>
              </SidebarMenuItem>
            </SidebarMenu>
          </SidebarFooter>
        </SidebarContent>
      </Sidebar>
    </SidebarProvider>
  );
};

export default AppSideBar;
