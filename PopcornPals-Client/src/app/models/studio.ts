import { Media } from "./media";

export interface Studio {
    id: number,
    name: string,
    description?: string, 
    foundingDate?: Date,
    media?: Media[]
}