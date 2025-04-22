export interface UserDTO {

    dni: string;
    name: string;
    number: number;
    email: string;
    admin: boolean;
    roles: UserRole[];


}
export enum UserRole {

ROLE_ADMIN, ROLE_USER

}