import { BrowserRouter, Route, Routes } from "react-router-dom";

import LogIn from "@/components/custom/auth/LogIn";
import SignUp from "@/components/custom/auth/SignUp";
import ForgotPassword from "./components/custom/auth/ForgotPassword";
import { Toaster } from "sonner";
import Home from "./components/custom/Home/Home";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/forgotpassword" element={<ForgotPassword />} />
        <Route path="/" element={<Home />} />
      </Routes>
      <Toaster position="top-right" richColors />
    </BrowserRouter>
  );
};
export default App;
