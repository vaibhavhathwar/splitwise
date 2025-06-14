import type { LogInForm, LoginRes } from "@/types/authTypes";
import axios from "axios";

const BASE_API = "http://localhost:8080/api/auth/";

export const checkUserExist = async (email: string): Promise<boolean> => {
  try {
    const res = await axios.get(`${BASE_API}/userexist`, { params: { email } });
    return res.data;
  } catch (err) {
    throw err;
  }
};

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
