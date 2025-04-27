export interface UserDTO {

    dni: string;
    name: string;
    number: number;
    email: string;
    admin: boolean;
    roles: UserRole[];


}
export interface RegisteringUserDTO{
    dni: string;
    name: string;
    number: number;
    email: string;
    password : string;



}


export enum UserRole {

    ROLE_ADMIN = "ROLE_ADMIN",
    ROLE_USER = "ROLE_USER"

}

export interface LoggedUserDTO {
    name: string;
    roles: UserRole[];
    token: string;

}

export interface AuthenticationRequest {
    email: string;
    password: string;
}

export interface AuthenticationResponse {
    jwt: string;
    roles: string[];
}
