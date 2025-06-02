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
import { checkUserExist } from "@/services/authApi";

const LogIn = () => {
  const [isUser, setIsUser] = useState(false);
  const [isChecked, setIsChecked] = useState(true);
  const [formContent, setFormContent] = useState({
    email: "",
    username: "",
    password: "",
    repeatPassword: "",
  });
  const onClickUserCheck = async () => {
    try {
      const res = await checkUserExist(formContent.email);
      setIsUser(res);
    } catch (err) {
      throw err;
    }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <Card className="w-100">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl">
            <b>Welcome to SplitWise</b>
          </CardTitle>
          <CardDescription>Enter your email to continue</CardDescription>
        </CardHeader>
        <CardContent className="flex flex-col gap-3">
          <Label htmlFor="email">Email</Label>
          <Input type="email" id="email" placeholder="Email" />
          <div className={`flex flex-col gap-3 ${!isChecked ? "hidden" : ""}`}>
            {!isUser ? (
              <>
                <Label htmlFor="username">Username</Label>
                <Input type="text" id="username" placeholder="Username" />
              </>
            ) : (
              ""
            )}
            <Label htmlFor="password1">Password</Label>
            <Input type="password" id="password1" placeholder="Password" />
            {!isUser ? (
              <>
                <Label htmlFor="password2">Repeat Password</Label>
                <Input
                  type="password"
                  id="password2"
                  placeholder="Repeat Password"
                />
              </>
            ) : (
              ""
            )}
          </div>
        </CardContent>
        <CardFooter>
          <Button className="w-full">Continue</Button>
        </CardFooter>
      </Card>
    </div>
  );
};

export default LogIn;
