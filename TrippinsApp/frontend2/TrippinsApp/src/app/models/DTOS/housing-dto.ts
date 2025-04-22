export interface HousingDTO {


    code: number;
    location: string;
    name: string;
    image: string; //Now it will store here the url of the api endpoint of its image
    description: string;
    stars: number;
    acepted: boolean;
    tags: TagDTO[];


}

export interface TagDTO{

    id: number;

}