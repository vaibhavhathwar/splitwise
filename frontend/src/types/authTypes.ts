export interface LogInForm {
  email: string;
  password: string;
}

export interface LogInFormErrors {
  email: string;
  password: string;
}

export interface SignUpForm {
  email: string;
  username: string;
  password: string;
  repeatPassword: string;
}

export interface SignUpFormErrors {
  email: string;
  username: string;
  password: string;
  repeatPassword: string;
}

export interface PasswordResetForm {
  password: string;
  repeatPassword: string;
}

export interface PasswordResetFormErrors {
  password: string;
  repeatPassword: string;
}

export interface LoginRes {
  message: string;
  success: boolean;
}

export interface SignupRes {
  message: string;
  success: boolean;
}
