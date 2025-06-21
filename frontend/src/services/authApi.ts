import type {
  CheckEmailRes,
  LogInForm,
  LoginRes,
  SignUpForm,
  SignupRes,
  VerifyOtpRes,
  UpdatePasswordRes,
} from "@/types/authTypes";
import axios from "axios";

const BASE_API = "http://localhost:8080/api/auth/";

export const loginApi = async (formContent: LogInForm): Promise<LoginRes> => {
  try {
    const res = await axios.post(`${BASE_API}login`, {
      ...formContent,
    });
    return res.data;
  } catch (err) {
    throw err;
  }
};

export const signupApi = async (
  formContent: SignUpForm
): Promise<SignupRes> => {
  try {
    const res = await axios.post(`${BASE_API}signup`, { ...formContent });
    return res.data;
  } catch (err) {
    throw err;
  }
};

export const checkemailApi = async (email: string): Promise<CheckEmailRes> => {
  try {
    const res = await axios.post(`${BASE_API}checkemail`, { email: email });
    return res.data;
  } catch (err) {
    throw err;
  }
};

export const verifyOtpApi = async (
  email: string,
  otp: string
): Promise<VerifyOtpRes> => {
  try {
    const res = await axios.post(`${BASE_API}verifyotp`, {
      email: email,
      otp: otp,
    });
    return res.data;
  } catch (err) {
    throw err;
  }
};

export const updatePasswordApi = async (
  email: string,
  password: string,
  repeatPassword: string,
  token: string
): Promise<UpdatePasswordRes> => {
  try {
    const res = await axios.post(`${BASE_API}updatepassword`, {
      email,
      password,
      repeatPassword,
      token,
    });
    return res.data;
  } catch (err) {
    throw err;
  }
};
