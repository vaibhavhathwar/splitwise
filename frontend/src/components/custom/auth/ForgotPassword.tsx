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
import type {
  PasswordResetForm,
  PasswordResetFormErrors,
} from "@/types/authTypes";
import { Link } from "react-router-dom";
const ForgotPassword = () => {
  const [stage, setStage] = useState<number>(1);
  const [email, setEmail] = useState<string>("");
  const [emailError, setEmailError] = useState<string>("");
  const [otp, setOtp] = useState<number>();
  const [otpError, setOtpError] = useState<string>();
  const [password, setPassword] = useState<PasswordResetForm>({
    password: "",
    repeatPassword: "",
  });
  const [passwordError, setPasswordError] = useState<PasswordResetFormErrors>({
    password: "",
    repeatPassword: "",
  });

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setPassword((prev) => ({
      ...prev,
      [name]: value,
    }));
  };
  const getOtp = () => {
    const emailRegex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    let error = "";
    if (email === "") {
      error = "Please enter the email";
    } else if (!emailRegex.test(email)) {
      error = "Please enter valid email address";
    }
    setEmailError(error);
    if (error === "") {
      setStage(2);
    }
  };
  const verifyOtp = () => {
    let error = "";
    if (otp) {
      if (otp.toString().length === 6) {
        if (true) console.log("OTP varified");
        else error = "Wrong otp";
      } else {
        error = "OTP length should be 6";
        setOtpError(error);
      }
    } else {
      error = "Please enter OTP";
    }
    setOtpError(error);
    if ((error = "")) {
      setStage(3);
    }
  };
  const resetPassword = () => {
    const error: PasswordResetFormErrors = { password: "", repeatPassword: "" };
    if (password.password === "") {
      error.password = "Please enter the password";
    } else if (password.repeatPassword === "") {
      error.repeatPassword = "Please repeat the password";
    } else if (password.password != password.repeatPassword) {
      error.repeatPassword = "Both the password should match";
    }
    setPasswordError(error);
  };
  return (
    <div className="flex justify-center items-center h-screen">
      <Card className="w-100">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl">
            <b>Welcome to SplitWise</b>
          </CardTitle>
          <CardDescription className="text-xl">Forgot Password</CardDescription>
        </CardHeader>
        <CardContent className="flex flex-col gap-3">
          {stage === 1 ? (
            <>
              {" "}
              <Label htmlFor="email">Email</Label>
              <Input
                type="email"
                id="email"
                name="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              {emailError && (
                <p className="text-sm text-red-500">{emailError}</p>
              )}
              <Button className="w-full" onClick={getOtp}>
                Get OTP
              </Button>
            </>
          ) : (
            ""
          )}
          {stage === 2 ? (
            <>
              {" "}
              <Label htmlFor="otp">OTP</Label>
              <Input
                type="number"
                id="otp"
                name="otp"
                placeholder="OTP"
                value={otp}
                onChange={(e) => setOtp(Number(e.target.value))}
              />
              {otpError && <p className="text-sm text-red-500">{otpError}</p>}
              <Button className="w-full" onClick={verifyOtp}>
                Verify OTP
              </Button>
            </>
          ) : (
            ""
          )}
          {stage === 3 ? (
            <>
              <Label htmlFor="password">New Password</Label>
              <Input
                type="password"
                id="password"
                name="password"
                placeholder="New Password"
                value={password.password}
                onChange={(e) => handlePasswordChange(e)}
              />
              {passwordError.password && (
                <p className="text-sm text-red-500">{passwordError.password}</p>
              )}
              <Label htmlFor="repeatPassword">Repeat New Password</Label>
              <Input
                type="repeatPassword"
                id="repeatPassword"
                name="repeatPassword"
                placeholder="Repeat New Password"
                value={password.repeatPassword}
                onChange={(e) => handlePasswordChange(e)}
              />
              {passwordError.repeatPassword && (
                <p className="text-sm text-red-500">
                  {passwordError.repeatPassword}
                </p>
              )}
              <Button className="w-full" onClick={resetPassword}>
                Reset Password
              </Button>
            </>
          ) : (
            ""
          )}
          <div className="flex justify-between w-full mt-2">
            <Link
              to="/login"
              className=" text-sm underline-offset-4 hover:underline"
            >
              Have an account? Login.
            </Link>
            <Link
              to="/signup"
              className=" text-sm underline-offset-4 hover:underline"
            >
              New to SplitWise? SignUp.
            </Link>
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default ForgotPassword;
