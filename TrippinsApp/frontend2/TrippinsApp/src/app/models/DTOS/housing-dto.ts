export interface HousingDTO {


    code: number;
    location: string;
    name: string;
    image: string; //Now it will store here the url of the api endpoint of its image
    description: string;
    stars: number;
    acepted: boolean;
    tags: TagDTO[];
    price: number;


}

export interface TagDTO{

    id: number;

}

export interface PagedResponse<T> {
    content: T[];
    pageable: {
      pageNumber: number;
      pageSize: number;
    };
    last: boolean;
    totalElements: number;
    totalPages: number;
    size: number;
  }