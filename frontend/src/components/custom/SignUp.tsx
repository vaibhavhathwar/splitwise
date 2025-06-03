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

const SignUp = () => {
  const [formContent, setFormContent] = useState({
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
  const signUp = () => {
    console.log(formContent);
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

          <Label htmlFor="username">Username</Label>
          <Input
            type="text"
            id="username"
            name="username"
            placeholder="Username"
            value={formContent.username}
            onChange={handleFormChange}
          />

          <Label htmlFor="password1">Password</Label>
          <Input
            type="password"
            id="password"
            name="password"
            placeholder="Password"
            value={formContent.password}
            onChange={handleFormChange}
          />
          <Label htmlFor="password2">Repeat Password</Label>
          <Input
            type="password"
            id="repeatPassword"
            name="repeatPassword"
            placeholder="Repeat Password"
            value={formContent.repeatPassword}
            onChange={handleFormChange}
          />
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
