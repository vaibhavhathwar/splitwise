import { BrowserRouter, Route, Routes } from "react-router-dom";

import LogIn from "@/components/custom/auth/LogIn";
import SignUp from "@/components/custom/auth/SignUp";
import ForgotPassword from "./components/custom/auth/ForgotPassword";
import { Toaster } from "sonner";
import Dashboard from "./components/custom/Dashboard/Dashboard";
import Home from "./components/custom/Dashboard/Home";
import Groups from "./components/custom/Dashboard/Groups";
import Friends from "./components/custom/Dashboard/Friends";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/forgotpassword" element={<ForgotPassword />} />
        <Route path="/" element={<Dashboard />}>
          <Route index element={<Home />} />
          <Route path="groups" element={<Groups />} />
          <Route path="friends" element={<Friends />} />
        </Route>
      </Routes>
      <Toaster position="top-right" richColors />
    </BrowserRouter>
  );
};
export default App;
