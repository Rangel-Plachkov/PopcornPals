import { Studio } from "./studio";

export interface Media {
    id: number,
    type?: string,
    title?: string,
    genre?: string,
    releaseDate: Date,
    endDate?: Date,
    length?: number,
    description?: string,
    parent?: Media,
    children?: Media[],
    studio?: Studio
}