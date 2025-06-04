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
import type { SignUpForm, SignUpFormErrors } from "@/types/auth";

const SignUp = () => {
  const [formContent, setFormContent] = useState<SignUpForm>({
    email: "",
    username: "",
    password: "",
    repeatPassword: "",
  });
  const [errors, setErrors] = useState<SignUpFormErrors>({
    email: "",
    username: "",
    password: "",
    repeatPassword: "",
  });
  const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormContent((prev) => ({
      ...prev,
      [name]: value,
    }));
  };
  const signUp = (): void => {
    console.log(formContent);
    if (validateSignup(formContent)) {
      console.log("submitting");
    }
  };
  const validateSignup = ({
    email,
    username,
    password,
    repeatPassword,
  }: SignUpForm): boolean => {
    const emailRegex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const currError: SignUpFormErrors = {
      email: "",
      username: "",
      password: "",
      repeatPassword: "",
    };
    if (email === "") {
      currError.email = "Please enter the email";
    } else if (!emailRegex.test(email)) {
      currError.email = "Please enter valid email";
    }
    if (username === "") {
      currError.username = "Please enter the username";
    }
    if (password === "") {
      currError.password = "Please enter the password";
    } else if (repeatPassword === "") {
      currError.repeatPassword = "Please repeat the password";
    } else if (password != repeatPassword) {
      currError.repeatPassword = "Both the password should match";
    }
    setErrors(currError);
    if (
      currError.email != "" ||
      currError.username != "" ||
      currError.password != "" ||
      currError.repeatPassword != ""
    ) {
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
          <CardDescription className="text-xl">SignUp</CardDescription>
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
          <Label htmlFor="username">Username</Label>
          <Input
            type="text"
            id="username"
            name="username"
            placeholder="Username"
            value={formContent.username}
            onChange={handleFormChange}
          />
          {errors.username && (
            <p className="text-sm text-red-500">{errors.username}</p>
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
          <Label htmlFor="password2">Repeat Password</Label>
          <Input
            type="password"
            id="repeatPassword"
            name="repeatPassword"
            placeholder="Repeat Password"
            value={formContent.repeatPassword}
            onChange={handleFormChange}
          />
          {errors.repeatPassword && (
            <p className="text-sm text-red-500">{errors.repeatPassword}</p>
          )}
        </CardContent>
        <CardFooter>
          <Button className="w-full" onClick={signUp}>
            SignUp
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
};

export default SignUp;
