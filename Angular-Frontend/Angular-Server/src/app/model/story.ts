export class Story {

    // id: Number | undefined;
    title: string | undefined;
    description: string | undefined;
    status: {
        statusId: number;
    }  = {
        statusId: 0
    }
    priority: {
        priorityId: number
    } = {
        priorityId: 0
    };
    type: {
        storyTypeId: number
    } = {
        storyTypeId: 0
    }
    createdDate: any | undefined;
}
