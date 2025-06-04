import { BrowserRouter, Route, Routes } from "react-router-dom";

import LogIn from "@/components/custom/auth/LogIn";
import SignUp from "@/components/custom/auth/SignUp";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/signup" element={<SignUp />} />
      </Routes>
    </BrowserRouter>
  );
};
export default App;
