import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { useState } from "react";
import type { LogInForm, LogInFormErrors } from "@/types/authTypes";
import { Link } from "react-router-dom";
import { loginApi } from "@/services/authApi";
import { Terminal } from "lucide-react";
import { toast } from "sonner";
const LogIn = () => {
  const [formContent, setFormContent] = useState<LogInForm>({
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState<LogInFormErrors>({
    email: "",
    password: "",
  });
  const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormContent((prev) => ({
      ...prev,
      [name]: value,
    }));
  };
  const login = async () => {
    console.log(formContent);
    if (validateLogin(formContent)) {
      try {
        const res = await loginApi(formContent);
        if (!res.success) toast.error(res.message);
        else toast.success(res.message);
      } catch (err) {
        throw err;
      }
    }
  };
  const validateLogin = ({ email, password }: LogInForm): boolean => {
    const emailRegex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const newError = { email: "", password: "" };
    if (email === "") {
      newError.email = "Please enter the email";
    } else if (!emailRegex.test(email)) {
      newError.email = "Please enter valid email address";
    }
    if (password === "") {
      newError.password = "Please enter password";
    }
    setErrors(newError);
    if (newError.email != "" || newError.password != "") {
      return false;
    }
    return true;
  };
  return (
    <div className="flex justify-center items-center h-screen">
      <Card className="w-100">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl">
            <b>Welcome to SplitWise</b>
          </CardTitle>
          <CardDescription className="text-xl">LogIn</CardDescription>
        </CardHeader>
        <CardContent className="flex flex-col gap-3">
          <Label htmlFor="email">Email</Label>
          <Input
            type="email"
            id="email"
            name="email"
            placeholder="Email"
            value={formContent.email}
            onChange={handleFormChange}
          />
          {errors.email && (
            <p className="text-sm text-red-500">{errors.email}</p>
          )}
          <Label htmlFor="password1">Password</Label>
          <Input
            type="password"
            id="password"
            name="password"
            placeholder="Password"
            value={formContent.password}
            onChange={handleFormChange}
          />
          {errors.password && (
            <p className="text-sm text-red-500">{errors.password}</p>
          )}
        </CardContent>
        <CardFooter className="flex flex-col">
          <Button className="w-full" onClick={login}>
            LogIn
          </Button>
          <div className="flex justify-between w-full mt-2">
            <Link
              to="/forgotpassword"
              className=" text-sm underline-offset-4 hover:underline"
            >
              Forgot your password?
            </Link>
            <Link
              to="/signup"
              className=" text-sm underline-offset-4 hover:underline"
            >
              New to SplitWise? SignUp.
            </Link>
          </div>
        </CardFooter>
      </Card>
    </div>
  );
};

export default LogIn;
