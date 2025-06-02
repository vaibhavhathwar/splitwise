import LogIn from "@/components/custom/LogIn";
import { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

const App = () => {
  const [isLogedIn, setIsLogedIn] = useState(false);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LogIn />} />
      </Routes>
    </BrowserRouter>
  );
};
export default App;
