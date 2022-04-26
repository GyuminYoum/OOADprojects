import numpy as np
import copy


class Player:
    def __init__(self, name, color):
        self.name = name
        self.roads = {}
        self.settlement = []
        self.city = []
        self.resources = {}
        self.vp = 0
        self.card = []
        self.color = color
        self.longestroad = False
        self.largestarmy = False
        # self.command = None

    def getUnusedCards(self):
        list1 = []
        for x in self.card:
            if not x.used:
                list1.append(x)
        return list1

    def AvailableCardSummary(self):
        list1 = {}
        list2 = self.getUnusedCards()
        for x in list2:
            if x.name not in list1.keys():
                list1[x.name] = 1
            else:
                list1[x.name] += 1
        list1['Victory Point']=self.getVPCount()
        return list1

    def getVPCount(self):
        val=0
        for x in self.card:
            if x.name=='Victory Point':
                val+=1
        return val

    def checkifWin(self):
        val = self.getVPCount()+2*self.largestarmy+2*self.longestroad+len(self.settlement)+2*len(self.city)
        self.vp = val
        if val >= 10:
            return True
        else:
            return False

    def deckSummary(self):
        list1 = {}
        for x in self.card:
            if x.name not in list1.keys():
                list1[x.name]=1
            else:
                list1[x.name]+=1
        return list1

    def hasKnight(self):
        for x in self.card:
            if x.used==False and x.name=="Knight":
                return True
        return False

    def hasRoadBuilding(self):
        for x in self.card:
            if x.used==False and x.name=="Road Building":
                return True
        return False

    def hasYearOfPlenty(self):
        for x in self.card:
            if x.used==False and x.name=="Year of Plenty":
                return True
        return False

    def hasMonopoly(self):
        for x in self.card:
            if x.used==False and x.name=="Monopoly":
                return True
        return False

    def countKnights(self):
        count=0
        for x in self.card:
            if x.name=="Knight" and x.used==True:
                count+=1
        return count

    def useCard(self,cardname):
        for x in self.card:
            if x.name==cardname and x.used==False:
                x.used=True
                break

    def countDonation(self,hexname):
        val=0
        for x in self.settlement:
            for y in x.hex:
                if(y.name==hexname):
                    val+=1
        for x in self.city:
            for y in x.hex:
                if(y.name==hexname):
                    val+=1
        return val

    def canBuyCard(self):
        if (self.resources['sheep'] >= 1 and self.resources['ore'] >=1 and self.resources['wheat'] >= 1):
            return True
        return False

    def number_of_roads(self):
        return len(self.roads)

    def number_of_settlements(self):
        return len(self.settlement)

    def number_of_cities(self):
        return len(self.city)

    def get_settlementNames(self):
        name_list = []
        for x in self.settlement:
            name_list.append(x.label)
        return name_list

    def get_cityNames(self):
        name_list = []
        for x in self.city:
            name_list.append(x.label)
        return name_list

    def get_roadNames(self):
        name_dict = {}
        dict2 = copy.deepcopy(self.roads)
        for key, val in dict2.items():
            for x in val:
                if key.label not in name_dict.keys():
                    name_dict[key.label] = []
                if x.label not in name_dict.keys():
                    name_dict[x.label] = []
                if x.label not in name_dict[key.label]:
                    name_dict[key.label].append(x.label)
                if key.label not in name_dict[x.label]:
                    name_dict[x.label].append(key.label)
        return name_dict

    def generatePossibleSettlements(self):
        list1 = []
        # print("Player settlement: ")
        settlementNames = self.get_settlementNames()
        cityNames = self.get_cityNames()
        for key in self.roads.keys():
            if key.label not in settlementNames and key.label not in cityNames:
                if key not in list1:
                    list1.append(key)
        return list1

    def generateSettlementNameList(self):
        namelist = []
        settlementList = self.generatePossibleSettlements()
        print("list possible settlements: ")
        # for x in settlementList:
        #     print(x.label)
        # print("current settlement: ")
        # for x in self.settlement:
        #     print(x.label)

        for node in settlementList:
            if node.label not in namelist:
                namelist.append(node.label)
        # print("namelist: "+str(namelist))
        return namelist

    def getSettlementName(self):
        list1 = []
        for x in self.settlement:
            list1.append(x.label)
        return list1

    def generatePossibleRoads(self):
        possible_roads = {}
        list1 = []
        for x in self.settlement:
            list1.append(x)
        for x in self.city:
            list1.append(x)
        for node in self.roads.keys():
            if node not in list1:
                list1.append(node)
        for node in list1:
            possible_roads[node] = []
            for adj in node.adj:
                if adj not in possible_roads.keys():
                    possible_roads[adj] = []
                if adj not in possible_roads[node]:
                    possible_roads[node].append(adj)
                if node not in possible_roads[adj]:
                    possible_roads[adj].append(node)

        return possible_roads

    def generateRoadNameList(self):
        road_name_list = []
        road_dict = self.generatePossibleRoads()

        for key, value in road_dict.items():
            for x in value:
                road_name = key.label+x.label
                # print("gRL")
                # print(road_name)
                reverse = x.label+key.label
                if road_name not in road_name_list and reverse not in road_name_list:
                    road_name_list.append(road_name)
                    road_dict[key].remove(x)
                    # print(x.label+" is removed from "+key.label)
                    road_dict[x].remove(key)
                    # print(key.label + " is removed from " + x.label)

        return road_name_list

    # def generateRoadNameList(self):
    #     road_list = []
    #     for node in self.settlement:
    #         for adj in node.adj:
    #             road_list.append(node.label + adj.label)
    #     for node in self.city:
    #         for adj in node.adj:
    #             road_list.append(node.label+adj.label)
    #
    #     dict2 = copy.deepcopy(self.roads)
    #     #print(f'dict2: {dict2}')
    #     for road_key in dict2.keys():
    #         for adj_nodes in dict2[road_key]:
    #             print(road_key.label, adj_nodes.label)
    #             if(road_key.label > adj_nodes.label):
    #                 road_name=adj_nodes.label+road_key.label
    #             else:
    #                 road_name=road_key.label+adj_nodes.label
    #             road_list.append(road_name)
    #             dict2[road_key].remove(adj_nodes)
    #             dict2[adj_nodes].remove(road_key)
    #
    #     return road_list

    def canBuildSettlement(self):
        if self.resources['sheep'] >= 1 and self.resources['wood'] >= 1 and self.resources['clay'] >= 1 and self.resources['wheat'] >= 1:
            return True
        else:
            return False

    def canBuildCity(self):
        if self.resources['ore'] >= 3 and self.resources['wheat'] >= 2:
            return True
        else:
            return False

    def canBuildRoad(self):
        if self.resources['clay'] >= 1 and self.resources['wood'] >= 1:
            return True
        else:
            return False

    def totalResources(self):
        count = 0
        for value in self.resources.values():
            count += value
        return count
