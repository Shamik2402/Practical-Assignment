export class Story {

    // id: Number | undefined;
    title: string  = "";
    description: string  = "";
    status: {
        statusName: any;
        statusId: number;
    }  = {
        statusId: 0,
        statusName: ""
    }
    priority: {
        priorityLevel: any;
        priorityId: number
    } = {
        priorityId: 0,
        priorityLevel: ""
    };
    type: {
        storyType: any;
        storyTypeId: number
    } = {
        storyTypeId: 0,
        storyType: ""
    }
    createdDate: any | undefined;
    createdBy: {
        id: number;
        password: string;
        reportsTo: string;
        role: {
            id: number;
            role: string;
        };
        team: {
            id: number;
            name: string;
        };
        username: string;
    } = {
        id: 0,
        password: "",
        reportsTo: "",
        role: {
            id: 0,
            role: ""
        },
        team: {
            id: 0,
            name: ""
        },
        username: ""
    }
    assignedTo: {
        id: number;
        password: string;
        reportsTo: string;
        role: {
            id: number;
            role: string;
        };
        team: {
            id: number;
            name: string;
        };
        username: string;
    } = {
        id: 0,
        password: "",
        reportsTo: "",
        role: {
            id: 0,
            role: ""
        },
        team: {
            id: 0,
            name: ""
        },
        username: ""
    };
    team: {
        id: number;
        name: string;
    } = {
        id: 0,
        name: ""
    }
}
