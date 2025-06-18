import type {
  LogInForm,
  LoginRes,
  SignUpForm,
  SignupRes,
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
