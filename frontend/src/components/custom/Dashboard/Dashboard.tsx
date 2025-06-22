import { Outlet } from "react-router-dom";
import AppSideBar from "./components/AppSideBar";

const Dashboard = () => {
  return (
    <div className="flex">
      <AppSideBar />
      <main className="flex-1 p-4">
        <Outlet />
      </main>
    </div>
  );
};
export default Dashboard;
