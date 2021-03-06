//
//  DoubleFilterBCScreen1DSItem.swift
//  FilterSortTest
//
//  This App has been generated using IBM Mobile App Builder
//

import Foundation

enum DoubleFilterBCScreen1DSItemMapping {
	static let text1 = "text1"
	static let text2 = "text2"
	static let picture = "picture"
	static let text3 = "text3"
	static let id = "id"
}

public class DoubleFilterBCScreen1DSItem : Item {
	
	var text1 : String?
	var text2 : String?
	var picture : String?
	var text3 : String?
	var id : String?
	
	// MAR: - <Item>

	public var identifier: AnyObject? {
		guard let identifier = id else {
			return ""
		}
		return identifier
	}
	
	required public init?(dictionary: NSDictionary?) {
    
        retrieve(dictionary)
    }
	
	public func retrieve(dictionary: NSDictionary?) {
     
        guard let dic = dictionary else {
            return
        }
        
 		text1 = dic[DoubleFilterBCScreen1DSItemMapping.text1] as? String
		
		text2 = dic[DoubleFilterBCScreen1DSItemMapping.text2] as? String
		
		picture = dic[DoubleFilterBCScreen1DSItemMapping.picture] as? String
		
		text3 = dic[DoubleFilterBCScreen1DSItemMapping.text3] as? String
		
		id = dic[DoubleFilterBCScreen1DSItemMapping.id] as? String
		
	   
    }
}
	
